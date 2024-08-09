package com.example.ecommerce.service.algorithm.search;

import jakarta.persistence.criteria.Predicate;

public class ProductFilterCategoryChildren extends ProductFilterStrategy {

    public ProductFilterCategoryChildren(FilterData filterData) {
        super(filterData);
    }

    @Override
    public Predicate filter() {
        String[] ids = filterData.getData().split(";");
        Predicate predicate = null;
        for (String id : ids) {
            filterData.setData(id);
            Predicate pre = ProductFilterCommon.hasJoinEqual("category", "id", filterData);
            predicate = predicate != null ? filterData.getCriteriaBuilder().or(pre, predicate) : pre;
        }
        return predicate;
    }
}
