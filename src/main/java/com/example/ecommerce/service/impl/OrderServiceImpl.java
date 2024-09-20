package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.auth.Customer;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.order.*;
import com.example.ecommerce.domain.entities.order.states.OrderStateFactory;
import com.example.ecommerce.domain.entities.order.states.OrderStateMessage;
import com.example.ecommerce.domain.entities.order.states.ProcessingState;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.binding.FilterOrderRequest;
import com.example.ecommerce.domain.model.binding.ItemRequest;
import com.example.ecommerce.domain.model.binding.OrderRequest;
import com.example.ecommerce.domain.model.modelviews.order.OrderModelView;
import com.example.ecommerce.service.event.Event;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.ResourcesNotFoundException;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.ecommerce.service.event.Event.EventType.*;
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
                .stateName(ProcessingState.class.getSimpleName())
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
                                            throw new ResourcesNotFoundException("Item out of bound inventory");
                                        })
                                        .collect(Collectors.toSet()))
                                .build())
                        .collect(Collectors.toSet()))
                .customer(Customer.builder().id(user.getEntityType().getEntityId()).build())
                .orderStateMessages(new ArrayList<>(
                        List.of(OrderStateMessage.builder()
                                .message(OrderStateFactory
                                        .getState(ProcessingState.class.getSimpleName())
                                        .status())
                                .build())
                ))
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
        return apiResponse("cretead order", new OrderModelView(order));
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
        postNotificationEvent(ORDER_DELETE, order);
        return apiResponse("delete order success", null);
    }

    @Override
    public APIResponse<?> updateOrderState(Long orderId, boolean isNext) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourcesNotFoundException("Order not found"));
        String message = "";
        if (isNext) {
            message = OrderStateFactory.getState(order.getStateName()).next(order);
        } else {
            message = OrderStateFactory.getState(order.getStateName()).prev(order);
        }
        OrderStateMessage orderStateMessage = OrderStateMessage.builder()
                .message(message)
                .build();
        order.addOrderStateMessage(orderStateMessage);
        orderRepository.save(order);
        return apiResponse("state was updated to " + order.getStateName(), null);
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
