package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.auth.Customer;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.order.*;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.binding.FilterOrderRequest;
import com.example.ecommerce.domain.model.binding.ItemRequest;
import com.example.ecommerce.domain.model.binding.OrderRequest;
import com.example.ecommerce.domain.model.modelviews.order.OrderViewModel;
import com.example.ecommerce.service.event.Event;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.service.timer.TimerInfo;
import com.example.ecommerce.service.timer.TimerService;
import com.example.ecommerce.service.timer.job.OrderApprovalJob;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ecommerce.service.event.Event.EventType.*;
import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final LineItemRepository lineItemRepository;
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final TimerService timerService;
    @Override
    public APIResponse<?> createOrder(OrderRequest request) {
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
        timerService.schedulerJob(
                OrderApprovalJob.class,
                //offsetMinis: 8h; job execute when created order push 8h
                new TimerInfo(
                        1,
                        5000l,
                        8*60*60*1000l,
                        false,
                        order.getId().toString())
        );
        return apiResponse("cretead order",null);
    }


    @Override
    public APIListResponse<?> getAllOrderByCustomer(FilterOrderRequest request) {
        Page<Order> pageOrders;
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        if (request.getOrderStatus() != null && request.getOrderStatus() != OrderStatus.ALL) {
            pageOrders = orderRepository
                    .findAllByCreatedByAndOrderStatus(SecurityUtils.getUsername(), request.getOrderStatus(), pageable);
        } else {
            pageOrders = orderRepository.findAllByCreatedBy(SecurityUtils.getUsername(), pageable);
        }
        return new APIListResponse<>(
                "ok", 0, 1, 200, request.getPage(), request.getLimit(),
                pageOrders.getTotalPages(),
                pageOrders.getContent().stream().map(o -> new OrderViewModel(o)).toList()
        );
    }

    @Override
    public APIResponse<?> updatePayment(Long orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new GeneralException("Not found order"));
        order.setPurchased(true);
        orderRepository.save(order);
        postNotificationEvent(ORDER_PAYMENT, order);
        return apiResponse("update payment success", null);
    }

    @Override
    public APIResponse<?> deleteById(Long orderId) {
        List<LineItem> lineItems = lineItemRepository.findAllByOrderId(orderId);
        lineItems.stream().forEach(lineItem -> {
            itemRepository.deleteByLineItem_Id(lineItem.getId());
        });
        lineItemRepository.deleteAll(lineItems);
        Order order = lineItems.get(0).getOrder();
        orderRepository.delete(order);
        postNotificationEvent(ORDER_DELETE, order);
        return apiResponse("delete order success", null);
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
