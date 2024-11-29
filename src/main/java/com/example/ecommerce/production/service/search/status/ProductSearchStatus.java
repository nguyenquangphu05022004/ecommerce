package com.example.ecommerce.production.service.search.status;

import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.production.service.search.ProductSearchStrategy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Product is used or not
 */
public class ProductSearchStatus implements ProductSearchStrategy {
    @Override
    public Predicate search(Root<ProductSpu> root, CriteriaBuilder builder, String jsonData) {
        return null;
    }
}
