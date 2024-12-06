package com.example.ecommerce.product.service.search;

import com.example.ecommerce.frame.common.Factory;
import com.example.ecommerce.frame.common.string.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ProductSearchFactory {
    private static final Map<String, ProductSearchStrategy> MAP = new HashMap<>();

    public static ProductSearchStrategy getInstance(String key) {
            String className =  "ProductSearch" + StringUtils.title(key);
            String address = ProductSearchFactory.class.getPackageName() + "." + key + "." + className;
            if(!MAP.containsKey(key)) {
                Object instance = Factory.buildInstance(address, new Object[]{});
                MAP.put(key, (ProductSearchStrategy) instance);
            }
            return MAP.get(key);
    }

}
