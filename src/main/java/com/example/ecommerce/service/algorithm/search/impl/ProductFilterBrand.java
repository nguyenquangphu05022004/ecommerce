package com.example.ecommerce.service.algorithm.search.impl;

import com.example.ecommerce.service.algorithm.search.ProductFilterStrategy;
import jakarta.persistence.criteria.Predicate;

public class ProductFilterBrand implements ProductFilterStrategy {
    @Override
    public Predicate filter(String value) {
        return null;
    }
}
