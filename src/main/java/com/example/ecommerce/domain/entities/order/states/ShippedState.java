package com.example.ecommerce.domain.entities.order.states;

import com.example.ecommerce.domain.entities.order.Order;

public class ShippedState implements OrderState {
    @Override
    public String next(Order order) {
        order.setOrderStatus(ShippedState.class.getSimpleName());
        return String.format("Order state from %s to %s",
                order.getOrderStatus(),
                ShippedState.class.getSimpleName());
    }

    @Override
    public String prev(Order order) {
        order.setOrderStatus(ProcessingState.class.getSimpleName());
        return String.format("Order state from %s to %s",
                order.getOrderStatus(),
                ProcessingState.class.getSimpleName());
    }

    @Override
    public String status() {
        return "Shipped Status";
    }
}
