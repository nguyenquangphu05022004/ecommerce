package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Product;

public class ProductConverterImpl implements IGenericConverter<Product, ProductDto> {
    @Override
    public Product toEntity(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto toDto(Product product) {
        return null;
    }

    @Override
    public Product toEntity(Product product, ProductDto productDto) {
        return null;
    }
}
