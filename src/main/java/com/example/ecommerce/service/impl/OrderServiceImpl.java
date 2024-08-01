package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.order.*;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.binding.OrderRequest;
import com.example.ecommerce.domain.model.modelviews.order.OrderViewModel;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final NotificationRepository notificationRepository;
    private final EventRepository eventRepository;
    private final LineItemRepository lineItemRepository;
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;
    @Override
    public void createOrder(OrderRequest request) {
        Order order = Order.builder()
                .orderStatus(OrderStatus.NOT_APPROVAL)
                .payment(request.getPayment())
                .approval(false)
                .received(false)
                .purchased(false)
                .lineItems(request.getLineItems().stream()
                        .map(lineItem -> LineItem.builder()
                                .vendor(new Vendor(lineItem.getVendorId()))
                                .coupon(lineItem.getCouponId() != null ? new Coupon(lineItem.getCouponId()) : null)
                                .items(lineItem.getItems().stream().map(item -> {
                                            if (checkStockExists(item)) {
                                                return Item.builder()
                                                        .productInventory(new ProductInventory(item.getInventoryId()))
                                                        .quantity(item.getQuantity())
                                                        .build();
                                            }
                                            throw new NotFoundException("quantity out of bound in inventory");
                                        })
                                        .collect(Collectors.toSet()))
                                .build())
                        .collect(Collectors.toSet()))
                .build();
        orderRepository.save(order);
    }


    @Override
    public List<OrderViewModel> getAllOrderByCustomer(OrderStatus status) {
        List<Order> listOrder;
        if (status != OrderStatus.ALL) {
            listOrder = orderRepository
                    .findAllByCreatedByAndOrderStatus(SecurityUtils.username(), status);
        } else {
            listOrder = orderRepository.findAllByCreatedBy(SecurityUtils.username());
        }
        return listOrder.stream().map(order -> new OrderViewModel(order))
                .toList();
    }

    @Override
    public void updatePayment(Long orderId) {

    }

    @Override
    public void deleteById(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    private boolean checkStockExists(OrderRequest.ItemRequest itemRequest) {
        ProductInventory inventory = inventoryRepository.findById(itemRequest.getInventoryId())
                .orElseThrow(() -> new GeneralException("Inventory not found"));
        if (inventory.getQuantity() >= itemRequest.getQuantity() ) {
            inventory.setQuantity(inventory.getQuantity() - itemRequest.getQuantity());
            inventoryRepository.save(inventory);
            return true;
        }
        throw new NotFoundException(String.format("Item with name %s not found at inventory"));
    }
}
