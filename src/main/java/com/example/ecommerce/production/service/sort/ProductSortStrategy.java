package com.example.ecommerce.production.service.sort;

import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;

import java.util.List;

public interface ProductSortStrategy {
    void sort(List<ProductSpu> productSpus);
}
