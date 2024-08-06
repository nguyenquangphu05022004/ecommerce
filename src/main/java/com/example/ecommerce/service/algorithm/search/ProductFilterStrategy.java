package com.example.ecommerce.service.algorithm.search;

import jakarta.persistence.criteria.Predicate;

public interface ProductFilterStrategy {
    Predicate filter(String value);
}
