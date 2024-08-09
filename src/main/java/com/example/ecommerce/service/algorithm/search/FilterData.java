package com.example.ecommerce.service.algorithm.search;

import com.example.ecommerce.domain.entities.product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FilterData {
    private CriteriaBuilder criteriaBuilder;
    private Root<Product> productRoot;
    private String data;
}
