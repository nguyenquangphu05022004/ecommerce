package com.example.ecommerce.product.service.sort;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;

import java.util.List;

public interface ProductSortStrategy {
    void sort(List<ProductSpu> productSpus);
}
