package com.example.ecommerce.service.algorithm.sort;

import com.example.ecommerce.domain.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class SortSold implements SortStrategy {
    @Override
    public List<Product> sort(List<Product> products) {
        products = new ArrayList<>(products);
        Collections.sort(products, (p1, p2) -> getWholeSold(p2) - getWholeSold(p1));
        return products;
    }

    private int getWholeSold(Product p1) {
        return p1.getProductInventories().stream()
                .flatMapToInt(i -> IntStream.of(i.getNumberOfProductSold()))
                .sum();
    }
}
