package com.example.ecommerce.product.service.search.category;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.service.search.ProductSearchStrategy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductSearchCategory implements ProductSearchStrategy {
    @Override
    public Predicate search(Root<ProductSpu> root, CriteriaBuilder builder, String jsonData) {
        Predicate equal = builder.equal(root.get("productCategory").get("id"), jsonData);
        return equal;
    }
}
