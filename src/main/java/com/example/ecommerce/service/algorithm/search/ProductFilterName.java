package com.example.ecommerce.service.algorithm.search;

import jakarta.persistence.criteria.Predicate;

public class ProductFilterName extends ProductFilterStrategy {

    public ProductFilterName(FilterData filterData) {
        super(filterData);
    }

    @Override
    public Predicate filter() {
        var likeWithNameVn = ProductFilterCommon.hasJoinEqual("language", "nameVn", filterData);
        var likeWithNameEn = ProductFilterCommon.hasJoinEqual("language", "nameVn", filterData);
        return filterData.getCriteriaBuilder().or(likeWithNameEn, likeWithNameVn);
    }

}
