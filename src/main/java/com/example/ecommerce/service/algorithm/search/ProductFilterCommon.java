package com.example.ecommerce.service.algorithm.search;

import com.example.ecommerce.domain.entities.product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductFilterCommon {
    protected static Predicate hasJoinLike(
            String joinAttr,
            String targetCompare,
            FilterData filterData
    ) {
        return filterData.getCriteriaBuilder().like(
                filterData.getProductRoot().join(joinAttr).get(targetCompare),
                "%" +filterData.getData() + "%"
        );
    }

    protected static Predicate hasJoinEqual(
            String joinAttr,
            String targetCompare,
            FilterData filterData
    ) {
        return filterData.getCriteriaBuilder().equal(
                filterData.getProductRoot().join(joinAttr).get(targetCompare),
                filterData.getData()
        );
    }
    protected static Predicate between(
            String targetCompare,
            FilterData filterData,
            Integer start,
            Integer end
    ) {
        return filterData.getCriteriaBuilder().between(
                filterData.getProductRoot().get(targetCompare),
                start, end
        );
    }
    protected static Predicate equal(
            String targetCompare,
            FilterData filterData
    ) {
        return filterData.getCriteriaBuilder().equal(
                filterData.getProductRoot().get(targetCompare),
                filterData.getData()
        );
    }

    private static Root<Product> castRoot(Object param) {
        return (Root<Product>) param;
    }

    private static CriteriaBuilder castCriteria(Object param) {
        return (CriteriaBuilder) param;
    }
}
