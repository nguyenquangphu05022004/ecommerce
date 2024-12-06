package com.example.ecommerce.product.service.search.keyword;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.service.search.ProductSearchStrategy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductSearchKeyword implements ProductSearchStrategy {
    @Override
    public Predicate search(Root<ProductSpu> root, CriteriaBuilder builder, String jsonData) {
        Predicate equal = builder.like(root.get("name"), "%" + jsonData + "%");
        return equal;
    }
}
