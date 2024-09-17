package com.example.ecommerce.service.algorithm.search.product;

import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NameFilter extends StrategyFilter {
    @Override
    public Predicate filter() {
        var likeWithNameVn = CommonFilter.like( "nameVn", dataFilter);
        var likeWithNameEn = CommonFilter.like("nameVn", dataFilter);
        return dataFilter.getCriteriaBuilder().or(likeWithNameEn, likeWithNameVn);
    }
}
