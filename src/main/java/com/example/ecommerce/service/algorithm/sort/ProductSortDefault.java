package com.example.ecommerce.service.algorithm.sort;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductSortDefault implements ProductSortStrategy {
    @Override
    public List<Product> sort(List<Product> products) {
        products = new ArrayList<>(products);
        Collections.sort(products, Comparator.comparing(BaseEntity::getId));
        return products;
    }
}
