package com.example.ecommerce.service.algorithm.sort;

import com.example.ecommerce.domain.entities.product.Product;

import java.util.List;

public interface ProductSortStrategy {
    List<Product> sort(List<Product> products);
}
