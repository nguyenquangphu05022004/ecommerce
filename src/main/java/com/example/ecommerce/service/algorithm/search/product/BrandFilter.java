package com.example.ecommerce.service.algorithm.search.product;

import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrandFilter extends StrategyFilter {

    @Override
    public Predicate filter() {
        return CommonFilter.hasJoinEqual("productBrand", "id", dataFilter);
    }
}
