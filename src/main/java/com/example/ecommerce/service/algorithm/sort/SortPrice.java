package com.example.ecommerce.service.algorithm.sort;

import com.example.ecommerce.domain.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortPrice implements SortStrategy{
    @Override
    public List<Product> sort(List<Product> products) {
        List<Product> modif =new ArrayList<>(products);
        Collections.sort(modif, (p1, p2) -> {
            int avg1 = p1.getProductInventories()
                    .stream()
                    .mapToInt(s -> s.getPrice())
                    .min()
                    .getAsInt();

            int avg2 = p2.getProductInventories()
                    .stream()
                    .mapToInt(s -> s.getPrice())
                    .min()
                    .getAsInt();
            return avg2 - avg1;
        });
        return modif;
    }
}
