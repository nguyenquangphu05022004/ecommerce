package com.example.ecommerce.event.listener;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.event.Observer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderListener {
    private final SimpMessagingTemplate messagingTemplate;

    public  Observer<Order> orderCreateListener() {
        return order -> {
            log.info(SecurityUtils.getUsername() + " created order success");
        };
    }
    public  Observer<Order> orderPaymentListener() {
        return order -> {
            log.info(SecurityUtils.getUsername() + " paid for the order with id " + order.getId());
        };
    }

    public  Observer<Order> orderDeleteListener() {
        return order -> {

        };
    }
}
