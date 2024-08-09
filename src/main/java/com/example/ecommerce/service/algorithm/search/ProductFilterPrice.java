package com.example.ecommerce.service.algorithm.search;

import jakarta.persistence.criteria.Predicate;

public class ProductFilterPrice extends ProductFilterStrategy {
    public ProductFilterPrice(FilterData filterData) {
        super(filterData);
    }

    @Override
    public Predicate filter() {
        String[] prices = filterData.getData().split(";");
        Integer s = Math.min(Integer.parseInt(prices[0]), Integer.parseInt(prices[1]));
        Integer e = Math.max(Integer.parseInt(prices[0]), Integer.parseInt(prices[1]));
        return ProductFilterCommon.between("price", filterData, s, e);
    }
}
