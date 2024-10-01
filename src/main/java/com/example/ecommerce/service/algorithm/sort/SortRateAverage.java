package com.example.ecommerce.service.algorithm.sort;

import com.example.ecommerce.domain.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class SortRateAverage implements SortStrategy{
    @Override
    public List<Product> sort(List<Product> products) {
        products = new ArrayList<>(products);
        Collections.sort(products, (p1, p2) -> getRateAverage(p2).compareTo(getRateAverage(p1)));
        return products;
    }
    private Double getRateAverage(Product p1) {
        return p1.getEvaluations().stream()
                .flatMapToInt(v -> IntStream.of(v.getRating()))
                .average().getAsDouble();
    }
}
