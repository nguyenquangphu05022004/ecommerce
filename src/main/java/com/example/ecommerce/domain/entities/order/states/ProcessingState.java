package com.example.ecommerce.domain.entities.order.states;

import com.example.ecommerce.domain.entities.order.Order;

public class ProcessingState implements OrderState {
    @Override
    public String next(Order order) {
        order.setOrderStatus(ProcessingState.class.getSimpleName());
        return String.format("Order state from %s to %s",
                order.getOrderStatus(),
                ShippedState.class.getSimpleName());
    }

    @Override
    public String prev(Order order) {
        return "The order is in its initial state";
    }

    @Override
    public String status() {
        return "Processing Status";
    }
}
