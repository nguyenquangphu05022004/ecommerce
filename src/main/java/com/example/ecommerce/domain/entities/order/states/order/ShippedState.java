package com.example.ecommerce.domain.entities.order.states.order;

import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.State;

public class ShippedState implements State<Order, String> {
    @Override
    public String next(Order order) {
        order.setStateName(ShippedState.class.getSimpleName());
        return String.format("Order state from %s to %s",
                order.getStateName(),
                ShippedState.class.getSimpleName());
    }

    @Override
    public String prev(Order order) {
        order.setStateName(ProcessingState.class.getSimpleName());
        return String.format("Order state from %s to %s",
                order.getStateName(),
                ProcessingState.class.getSimpleName());
    }

    @Override
    public String status() {
        return "Shipped Status";
    }
}
