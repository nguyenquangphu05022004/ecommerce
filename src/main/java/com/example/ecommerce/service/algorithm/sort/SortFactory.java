package com.example.ecommerce.service.algorithm.sort;

import com.example.ecommerce.domain.FactoryBuilder;

public class SortFactory extends FactoryBuilder<SortStrategy> {
    private static SortFactory sortFactory;

    public SortFactory(Class clazz) {
        super(clazz);

    }

    public static SortStrategy getSortStrategy(String type) {
        return getInstance().getInstance(type);
    }

    private static SortFactory getInstance() {
        if(sortFactory == null) {
            sortFactory = new SortFactory(SortFactory.class);
        }
        return sortFactory;
    }


}
