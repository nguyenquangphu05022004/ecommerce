package com.example.ecommerce.service.algorithm.search;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class  ProductFilterStrategy {
    protected FilterData filterData;
    public abstract Predicate filter();
}
