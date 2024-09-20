package com.example.ecommerce.domain.entities.order.states;

import com.example.ecommerce.domain.FactoryBuilder;
import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.State;

public class OrderStateFactory extends FactoryBuilder<State<Order, String>> {

    private static OrderStateFactory orderStateFactory;
    private OrderStateFactory(Class clazz) {
        super(clazz);
    }
    private static OrderStateFactory getInstance() {
        if(orderStateFactory == null) {
            orderStateFactory = new OrderStateFactory(OrderStateFactory.class);
        }
        return orderStateFactory;
    }
    public static State<Order, String> getState(String typeName) {
        return orderStateFactory.getInstance().getInstance(typeName);
    }

}
