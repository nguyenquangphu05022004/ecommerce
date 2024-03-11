package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Product;

import java.util.List;

public interface IProductService {
    ProductDto saveOrUpdate(ProductDto productDto, Long categoryId);
    List<ProductDto> searchProductsByName(String name);
    List<ProductDto> findProductByCategoryId(Long categoryId);
}
