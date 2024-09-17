package com.example.ecommerce.service.algorithm.search.product;

import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PriceFilter extends StrategyFilter {

    @Override
    public Predicate filter() {
        String[] prices = dataFilter.getData().split(";");
        Integer s = Math.min(Integer.parseInt(prices[0]), Integer.parseInt(prices[1]));
        Integer e = Math.max(Integer.parseInt(prices[0]), Integer.parseInt(prices[1]));
        return CommonFilter.between("price", dataFilter, s, e);
    }
}
