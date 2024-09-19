package com.example.ecommerce.service.algorithm.search.product;

import com.example.ecommerce.common.Factory;
import com.example.ecommerce.domain.FactoryBuilder;

import java.util.HashMap;
import java.util.Map;

public class FactoryFilter extends FactoryBuilder<StrategyFilter> {
    private static FactoryFilter factoryFilter;
    public FactoryFilter(Class clazz) {
        super(clazz);
    }
    private static FactoryFilter getInstance() {
        if(factoryFilter == null) {
            factoryFilter = new FactoryFilter(FactoryFilter.class);
        }
        return factoryFilter;
    }
    public static StrategyFilter getStrategyFilter(String typeName) {
        return factoryFilter.getInstance(typeName);
    }
}
