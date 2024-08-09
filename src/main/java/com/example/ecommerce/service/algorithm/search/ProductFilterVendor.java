package com.example.ecommerce.service.algorithm.search;

import jakarta.persistence.criteria.Predicate;

public class ProductFilterVendor extends ProductFilterStrategy {
    public ProductFilterVendor(FilterData filterData) {
        super(filterData);
    }

    @Override
    public Predicate filter() {
        return ProductFilterCommon.hasJoinEqual("vendor", "id", filterData);
    }
}
