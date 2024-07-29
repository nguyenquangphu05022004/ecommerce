package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.OrderStatus;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.service.dto.OrderDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;
    private final EventRepository eventRepository;
    private final StockClassificationRepository classificationRepository;
    private final LineItemRepository lineItemRepository;
    private final ItemRepository itemRepository;
    @Qualifier("orderMapper")
    private final IMapper<Order, OrderRequest, OrderDto> mapper;
    @Override
    @Transactional
    public void createOrder(OrderRequest request) {
        // check stock whether product exists
        if (request.getLineItemRequests() != null) {
            request.getLineItemRequests().forEach(line -> {
                       line.getItemRequests().forEach(item -> {
                           if (!checkStockExists(item)) {
                               throw new GeneralException(String.format("Stock with id: %s and StockClassification with id: %s is empty", item.getStockId(), item.getClassificationId()));
                           }
                       });
                    });
        }
        // convert to order entit
        Order order = mapper.toEntity(request);
        //save order of user to database
        Order saved = orderRepository.save(order);
        order.getLineItems().forEach(line -> {
            line.setOrder(saved);
            Order.LineItem lineItem = lineItemRepository.save(line);
            line.getItems().forEach(item -> {
                item.setLineItem(lineItem);
                itemRepository.save(item);
            });
        });
        //notify to user when a order was created by current user;
        saved.notify(notificationRepository);
        //event auto approval order after 8 hours
        eventRepository.createEvent(saved.getId());
    }


    @Override
    public List<OrderDto> getAllOrderByCustomer(OrderStatus status) {
        List<Order> listOrder = new ArrayList<>();
        if(status != OrderStatus.ALL) {
            listOrder = orderRepository
                    .findAllByCreatedByAndOrderStatus(SecurityUtils.username(), status);
        } else {
            listOrder = orderRepository.findAllByCreatedBy(SecurityUtils.username());
        }
        return mapper.toDtoList(listOrder);
    }

    @Override
    public void approval(Long orderId, Boolean approval) {
        orderRepository.updateApprovalById(orderId, approval);
    }

    @Override
    public void updatePayment(Long orderId) {

    }

    @Override
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    private boolean checkStockExists(OrderRequest.ItemRequest request) {
        Stock.StockClassification stockClassification = classificationRepository
                .findByIdAndStockId(
                        request.getClassificationId(),
                        request.getStockId())
                .orElseThrow(() -> new GeneralException("Product not found"));
        int remainQuantity = stockClassification.getQuantityOfProduct() - stockClassification.getSeller();
        if(request.getQuantity() < remainQuantity) {
            return true;
        }
        throw new NotFoundException(String.format("Item with name %s not found at inventory"));
    }
}
