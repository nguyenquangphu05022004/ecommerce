package com.example.ecommerce.domain.entities.order.states;

import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.State;

public class DeliveredState implements State<Order, String> {

    @Override
    public String next(Order order) {
        return "This order is already delivered.";
    }

    @Override
    public String prev(Order order) {
        order.setStateName(ShippedState.class.getSimpleName());
        return String.format("Order state from %s to %s",
                DeliveredState.class.getSimpleName(),
                ShippedState.class.getSimpleName());
    }

    @Override
    public String status() {
        return "Order has delivered successfully";
    }
}
