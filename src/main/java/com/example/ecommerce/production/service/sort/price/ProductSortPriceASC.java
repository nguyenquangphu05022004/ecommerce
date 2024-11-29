package com.example.ecommerce.production.service.sort.price;

import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.production.service.sort.ProductSortStrategy;

import java.util.Collections;
import java.util.List;

public class ProductSortPriceASC implements ProductSortStrategy {
    @Override
    public void sort(List<ProductSpu> productSpus) {
        Collections.sort(productSpus, (s1, s2) -> {
            return Common.compareTo(s1, s2, 0);
        });
    }
}
