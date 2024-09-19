package com.example.ecommerce.domain.entities.order.states.order;

import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.State;

public class ProcessingState implements State<Order, String> {
    @Override
    public String next(Order order) {
        order.setStateName(ProcessingState.class.getSimpleName());
        return String.format("Order state from %s to %s",
                order.getStateName(),
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
