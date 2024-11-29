package com.example.ecommerce.production.service.search;

import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public  interface ProductSearchStrategy {
    Predicate search(Root<ProductSpu> root, CriteriaBuilder builder, String jsonData);
}
