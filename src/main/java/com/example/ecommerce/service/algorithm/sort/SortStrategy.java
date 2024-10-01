package com.example.ecommerce.service.algorithm.sort;

import com.example.ecommerce.domain.entities.Product;

import java.util.List;

public interface SortStrategy {
    List<Product> sort(List<Product> products);
}
