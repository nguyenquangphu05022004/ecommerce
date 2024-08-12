package com.example.ecommerce.service.event.subscribe;

import com.example.ecommerce.service.event.listener.OrderListener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.ecommerce.service.event.Event.EventType.*;
import static com.example.ecommerce.service.event.Event.getInstance;

@Component
@RequiredArgsConstructor
public class OrderSubscribe {
    private final OrderListener orderListener;

    @PostConstruct
    public void initSubscribe() {
        getInstance().subscribe(ORDER_PAYMENT, orderListener.orderPaymentListener());
        getInstance().subscribe(ORDER_CREATE, orderListener.orderCreateListener());
    }
}
