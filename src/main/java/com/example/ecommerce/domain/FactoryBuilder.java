package com.example.ecommerce.domain;

import com.example.ecommerce.common.Factory;

import java.util.HashMap;
import java.util.Map;

public abstract class FactoryBuilder <T> {
    private Class clazz;
    private Map<String, T> orderStates;

    public FactoryBuilder(Class clazz) {
        this.clazz = clazz;
    }

    protected T getInstance(String typeName) {
        if (orderStates == null) {
            orderStates = new HashMap<>();
        }
        if (!orderStates.containsKey(typeName)) {
            orderStates.put(typeName, (T) Factory.buildInstance(
                    clazz,
                    typeName,
                    new Class[]{},
                    new Object[]{}
            ));
        }
        return orderStates.get(typeName);
    }
}
