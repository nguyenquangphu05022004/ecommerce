package com.example.ecommerce.service.algorithm.search.product;

import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CateChildrenFilter extends StrategyFilter {

    @Override
    public Predicate filter() {
        String[] ids = dataFilter.getData().split(";");
        Predicate predicate = null;
        for (String id : ids) {
            dataFilter.setData(id);
            Predicate pre = CommonFilter.hasJoinEqual("category", "id", dataFilter);
            predicate = predicate != null ? dataFilter.getCriteriaBuilder().or(pre, predicate) : pre;
        }
        return predicate;
    }
}
