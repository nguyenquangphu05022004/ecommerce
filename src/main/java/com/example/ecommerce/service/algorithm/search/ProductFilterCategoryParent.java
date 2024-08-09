package com.example.ecommerce.service.algorithm.search;

import jakarta.persistence.criteria.Predicate;

public class ProductFilterCategoryParent extends ProductFilterStrategy {

    public ProductFilterCategoryParent(FilterData filterData) {
        super(filterData);
    }

    @Override
    public Predicate filter() {
        return ProductFilterCommon.hasJoinEqual("category", "id", filterData);
    }
}
