package com.example.ecommerce.event.subscribe;

import com.example.ecommerce.event.listener.ProductListener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.ecommerce.event.Event.EventType.PRODUCT_CREATE;
import static com.example.ecommerce.event.Event.EventType.PRODUCT_INVENTORY_UPDATE_QUANTITY;
import static com.example.ecommerce.event.Event.getInstance;

@Component
@RequiredArgsConstructor
public class ProductSubscribe {
    private final ProductListener productListener;


    @PostConstruct
    public void initSubscribe() {
        getInstance().subscribe(PRODUCT_CREATE, productListener.createProductListener());
        getInstance().subscribe(PRODUCT_INVENTORY_UPDATE_QUANTITY, productListener.updateQuantityProduct());
    }
}
