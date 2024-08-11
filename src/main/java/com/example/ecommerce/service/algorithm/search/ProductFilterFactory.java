package com.example.ecommerce.service.algorithm.search;

import java.util.HashMap;
import java.util.Map;

public class ProductFilterFactory {

    private static final Map<ProductFilterType, ProductFilterStrategy> instances = new HashMap<>();

    public static ProductFilterStrategy getInstance(ProductFilterType filterType) {
        if(!instances.containsKey(filterType)) {
            instances.put(filterType, ProductFilterSimpleFactory.getInstance(filterType));
        }
        return instances.get(filterType);
    }

}
