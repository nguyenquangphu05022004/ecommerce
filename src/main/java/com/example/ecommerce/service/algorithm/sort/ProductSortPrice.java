package com.example.ecommerce.service.algorithm.sort;

import com.example.ecommerce.domain.entities.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductSortPrice implements ProductSortStrategy{
    @Override
    public List<Product> sort(List<Product> products) {
        List<Product> modif =new ArrayList<>(products);
        Collections.sort(modif, (p1, p2) -> p2.getPrice() - p1.getPrice());
        return modif;
    }
}
