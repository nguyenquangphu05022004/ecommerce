package com.example.ecommerce.service.algorithm.search.product;

import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PriceFilter extends StrategyFilter {

    @Override
    public Predicate filter() {
        String[] prices = dataFilter.getData().split(";");
        return dataFilter.getCriteriaBuilder()
                .between(dataFilter.getProductRoot()
                                .join("productInventories")
                                .get("price"),
                        Math.min(Long.parseLong(prices[0]), Long.parseLong(prices[1])),
                        Math.max(Long.parseLong(prices[0]), Long.parseLong(prices[1])));
    }
}
