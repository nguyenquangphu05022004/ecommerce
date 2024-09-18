package com.example.ecommerce.service.algorithm.search.product;

import com.example.ecommerce.common.Factory;

import java.util.HashMap;
import java.util.Map;

public class FactoryFilter {

    private static Map<String, StrategyFilter> filters;

    public static StrategyFilter getInstance(String filterType) {
        if (filters == null) {
            filters = new HashMap<>();
        }

        if (!filters.containsKey(filterType)) {
            filters.put(filterType, (StrategyFilter) Factory.buildInstance(
                    FactoryFilter.class,
                    filterType,
                    new Class[]{},
                    new Object[]{}
            ));
        }
        return filters.get(filterType);
    }

}
