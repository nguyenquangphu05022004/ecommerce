package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.auth.Customer;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.order.*;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.binding.order.ItemRequest;
import com.example.ecommerce.domain.model.binding.order.OrderRequest;
import com.example.ecommerce.domain.model.modelviews.order.OrderViewModel;
import com.example.ecommerce.event.Event;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ecommerce.event.Event.EventType.*;

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
    private final UserRepository userRepository;

    @Override
    public void createOrder(OrderRequest request) {
        User user = userRepository.findByUsernameIgnoreCase(
                SecurityUtils.getUsername()
        ).orElseThrow(() -> new UsernameNotFoundException("You aren't login"));

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
                                            throw new NotFoundException("Item out of bound inventory");
                                        })
                                        .collect(Collectors.toSet()))
                                .build())
                        .collect(Collectors.toSet()))
                .customer(Customer.builder().id(user.getUserTypeId()).build())
                .build();
        orderRepository.save(order);

        order.getLineItems().stream().forEach(l -> {
            l.setOrder(order);
            lineItemRepository.save(l);
            l.getItems().stream().forEach(i -> {
                i.setLineItem(l);
                itemRepository.save(i);
            });
        });
        postNotificationEvent(ORDER_CREATE, order);
    }


    @Override
    public List<OrderViewModel> getAllOrderByCustomer(OrderStatus status) {
        List<Order> listOrder;
        if (status != null && status != OrderStatus.ALL) {
            listOrder = orderRepository
                    .findAllByCreatedByAndOrderStatus(SecurityUtils.getUsername(), status);
        } else {
            listOrder = orderRepository.findAllByCreatedBy(SecurityUtils.getUsername());
        }
        return listOrder.stream().map(order -> new OrderViewModel(order))
                .toList();
    }

    @Override
    public void updatePayment(Long orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new GeneralException("Not found order"));
        postNotificationEvent(ORDER_PAYMENT, order);
    }

    @Override
    public void deleteById(Long orderId) {
        System.out.println("-----------------------delete order------------------");
        List<LineItem> lineItems = lineItemRepository.findAllByOrderId(orderId);
        lineItems.stream().forEach(lineItem -> {
            itemRepository.deleteByLineItem_Id(lineItem.getId());
        });
        lineItemRepository.deleteAll(lineItems);
        Order order = lineItems.get(0).getOrder();
        orderRepository.delete(order);
        postNotificationEvent(ORDER_DELETE, order);
    }


    private boolean checkStockExists(ItemRequest itemRequest) {
        ProductInventory inventory = inventoryRepository.findById(itemRequest.getInventoryId())
                .orElseThrow(() -> new GeneralException("Inventory not found"));
        if (inventory.getQuantity() >= itemRequest.getQuantity()) {
            inventory.setQuantity(inventory.getQuantity() - itemRequest.getQuantity());
            inventoryRepository.save(inventory);
            return true;
        }
        return false;
    }

    private void postNotificationEvent(Event.EventType eventType, Order order) {
        new Thread(() -> {
            Event.getInstance().postEvent(
                    eventType,
                    order);
        }).start();
    }
}
