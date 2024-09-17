package com.example.ecommerce.service.algorithm.search.product;

import com.example.ecommerce.domain.entities.product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DataFilter {
    private CriteriaBuilder criteriaBuilder;
    private Root<Product> productRoot;
    private String data;
}
