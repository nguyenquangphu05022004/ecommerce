package com.example.ecommerce.event.subscribe;

import com.example.ecommerce.event.listener.OrderListener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.ecommerce.event.Event.EventType.*;
import static com.example.ecommerce.event.Event.getInstance;

@Component
@RequiredArgsConstructor
public class OrderSubscribe {
    private final OrderListener orderListener;

    @PostConstruct
    public void initSubscribe() {
        getInstance().subscribe(ORDER_PAYMENT, orderListener.orderPaymentListener());
        getInstance().subscribe(ORDER_DELETE, orderListener.orderDeleteListener());
        getInstance().subscribe(ORDER_CREATE, orderListener.orderCreateListener());
    }
}
