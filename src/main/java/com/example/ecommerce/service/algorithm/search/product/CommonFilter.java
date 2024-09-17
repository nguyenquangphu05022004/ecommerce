package com.example.ecommerce.service.algorithm.search.product;

import jakarta.persistence.criteria.Predicate;

public class CommonFilter {
    protected static Predicate hasJoinLike(
            String joinAttr,
            String targetCompare,
            DataFilter dataFilter
    ) {
        return dataFilter.getCriteriaBuilder().like(
                dataFilter.getProductRoot().join(joinAttr).get(targetCompare),
                "%" + dataFilter.getData() + "%"
        );
    }

    protected static Predicate hasJoinEqual(
            String joinAttr,
            String targetCompare,
            DataFilter dataFilter
    ) {
        return dataFilter.getCriteriaBuilder().equal(
                dataFilter.getProductRoot().join(joinAttr).get(targetCompare),
                dataFilter.getData()
        );
    }
    protected static Predicate hasJoinEqual(
            String joinAttr,
            String targetOne,
            String targetTwo,
            DataFilter dataFilter
    ) {
        return dataFilter.getCriteriaBuilder().equal(
                dataFilter.getProductRoot().join(joinAttr).get(targetOne).get(targetTwo),
                dataFilter.getData()
        );
    }
    protected static Predicate between(
            String targetCompare,
            DataFilter dataFilter,
            Integer start,
            Integer end
    ) {
        return dataFilter.getCriteriaBuilder().between(
                dataFilter.getProductRoot().get(targetCompare),
                start, end
        );
    }
    protected static Predicate like(
            String targetCompare,
            DataFilter dataFilter
    ) {
        return dataFilter.getCriteriaBuilder().like(
                dataFilter.getProductRoot().get(targetCompare),
                "%" + dataFilter.getData() + "%"
        );
    }
}
