package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.*;
import com.example.ecommerce.domain.model.binding.FilterOrderRequest;
import com.example.ecommerce.domain.model.binding.ItemRequest;
import com.example.ecommerce.domain.model.binding.OrderRequest;
import com.example.ecommerce.domain.model.modelviews.order.OrderModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.ResourcesNotFoundException;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductInventoryRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.service.event.Event;
import com.example.ecommerce.service.timer.TimerInfo;
import com.example.ecommerce.service.timer.TimerService;
import com.example.ecommerce.service.timer.job.OrderApprovalJob;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static com.example.ecommerce.service.event.Event.EventType.ORDER_CREATE;
import static com.example.ecommerce.service.event.Event.EventType.ORDER_PAYMENT;
import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductInventoryRepository productInventoryRepository;
    private final UserRepository userRepository;
    private final TimerService timerService;

    @Override
    public APIResponse<?> createOrder(OrderRequest request) {
        User user = userRepository.findByUsernameIgnoreCase(
                SecurityUtils.getUsername()
        ).orElseThrow(() -> new UsernameNotFoundException("You aren't login"));

        Order order = Order.builder()
                .payment(request.getPayment())
                .lineItems(request.getLineItems().stream()
                        .map(lineItem -> LineItem.builder()
                                .vendor(Vendor.builder()
                                        .id(lineItem.getVendorId())
                                        .build())
                                .coupon(Coupon.builder()
                                        .id(lineItem.getCouponId())
                                        .build())
                                .items(lineItem.getItems()
                                        .stream()
                                        .map(item -> {
                                            if (checkStockExists(item)) {
                                                return Item.builder()
                                                        .productInventory(
                                                                ProductInventory.builder()
                                                                .id(item.getInventoryId())
                                                                .build())
                                                        .quantity(item.getQuantity())
                                                        .build();
                                            }
                                            throw new ResourcesNotFoundException("Item out of bound inventory");
                                        })
                                        .collect(Collectors.toSet()))
                                .build())
                        .collect(Collectors.toSet()))
                .customer((Customer) user)
                .state(Order.State.PENDING)
                .build();
        orderRepository.save(order);
        postNotificationEvent(ORDER_CREATE, order);
        timerService.schedulerJob(
                OrderApprovalJob.class,
                //offsetMinis: 8h; job execute when created order push 8h
                new TimerInfo(
                        1,
                        5000l,
                        8 * 60 * 60 * 1000l,
                        false,
                        order.getId().toString())
        );
        return apiResponse("created order", new OrderModelView(order));
    }


    @Override
    public APIListResponse<?> getAllOrderByCustomer(FilterOrderRequest request) {
        Page<Order> page = orderRepository.findAllByCreatedByAndStateName(
                SecurityUtils.getUsername(),
                request.getOrderStateName(),
                PageRequest.of(request.getPage() - 1, request.getLimit())
        );
        return new APIListResponse<>(
                "get all order by order state",
                200,
                request.getPage(),
                request.getLimit(),
                page.getTotalPages(),
                page.getContent().stream().map(OrderModelView::new).toList()
        );
    }

    @Override
    public APIResponse<?> updatePayment(Long orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new GeneralException("Not found order"));
        orderRepository.save(order);
        postNotificationEvent(ORDER_PAYMENT, order);
        return apiResponse("update payment success", null);
    }

    @Override
    public APIResponse<?> deleteById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourcesNotFoundException("Not found order"));
        orderRepository.delete(order);
        return apiResponse("delete order success", null);
    }

    @Override
    public APIResponse<?> updateOrderState(Long orderId, Order.State state) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourcesNotFoundException("Order not found"));
        order.setState(state);
        orderRepository.save(order);
        return apiResponse("state was updated to " + state, null);
    }


    private boolean checkStockExists(ItemRequest itemRequest) {
        ProductInventory inventory = productInventoryRepository.findById(itemRequest.getInventoryId())
                .orElseThrow(() -> new GeneralException("Inventory not found"));
        if (inventory.getQuantity() >= itemRequest.getQuantity()) {
            inventory.setQuantity(inventory.getQuantity() - itemRequest.getQuantity());
            productInventoryRepository.save(inventory);
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
