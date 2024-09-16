package com.example.ecommerce.domain.entities.order.states;

import com.example.ecommerce.handler.exception.GeneralException;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class OrderStateFactory {
    private static Map<String, OrderState> orderStates;

    public static OrderState getOrderState(String orderState) {
        if(orderStates == null) {
            orderStates = new HashMap<>();
        }

        if(!orderStates.containsKey(orderState)) {
            try {
                Class<?> clazz = Class.forName(orderState);
                Constructor<?> constructor = clazz.getConstructor(new Class[]{});
                OrderState state = (OrderState) constructor.newInstance();
                orderStates.put(orderState, state);
            } catch (Exception e) {
                throw new GeneralException("OrderState not found: " + orderState);
            }
        }
        return orderStates.get(orderState);
    }
}
