package com.example.ecommerce.service.algorithm.search;

import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductFilterBrand extends ProductFilterStrategy {

    public ProductFilterBrand(FilterData filterData) {
        super(filterData);
    }

    @Override
    public Predicate filter() {
        return ProductFilterCommon.hasJoinEqual("productBrand", "id", filterData);
    }
}
