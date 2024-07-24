package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.LineItem;
import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.OrderStatus;
import com.example.ecommerce.domain.StockClassification;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.NotFoundException;
import com.example.ecommerce.service.dto.LineItemDto;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.service.dto.OrderDto;
import com.example.ecommerce.service.mapper.IMapper;
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
    @Qualifier("orderMapper")
    private final IMapper<Order, OrderDto, OrderDto> mapper;
    @Qualifier("lineItemMapper")
    private final IMapper<LineItem, Object, LineItemDto> lineItemMapper;
    @Override
    @Transactional
    public void createOrder(OrderDto request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getFullName(), "fullName");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getDistrict(), "district");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getAddress(), "address");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getWard(), "ward");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getPhoneNumber(), "phoneNumber");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUserContactDetails().getProvince(), "province");
        ValidationUtils.fieldCheckNullOrEmpty(request.getPayment().toString(), "payment");


        // check stock whether product exists
        Set<LineItem> lineItems = new HashSet<>();
        if (request.getLineItems() != null) {
            request.getLineItems().forEach(line -> {
                       line.getItems().forEach(item -> {
                           if (checkStockExists(item)) {
                               lineItems.add(lineItemMapper.toEntity(line));
                           }
                       });
                    });
        }

        if(lineItems.size() == 0) throw new GeneralException("You can't order because you haven't choose product yet");
        // convert to order entity
        Order order = mapper.toEntity(request).toBuilder()
                .lineItems(lineItems)
                .build();
        //save order of user to database
        Order saved = orderRepository.save(order);
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

    private boolean checkStockExists(LineItemDto.ItemDto itemDto) {
        StockClassification stockClassification = classificationRepository
                .findByIdAndStockId(
                        itemDto.getStockClassification().getId(),
                        itemDto.getStock().getId())
                .orElseThrow(() -> new GeneralException("Product not found"));
        int remainQuantity = stockClassification.getQuantityOfProduct() - stockClassification.getSeller();
        if(itemDto.getQuantity() < remainQuantity) {
            return true;
        }
        throw new NotFoundException(String.format("Item with name %s not found at inventory"));
    }
}
