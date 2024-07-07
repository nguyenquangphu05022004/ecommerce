package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.dto.ProductDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("productMapper")
public class ProductMapper implements IMapper<Product, ProductRequest, ProductDto> {
    @Override
    public Product toEntity(ProductRequest productRequest) {
        return null;
    }

    @Override
    public ProductDto toDto(Product product) {
        return null;
    }

    @Override
    public List<ProductDto> toDtoList(List<Product> products) {
        return null;
    }
}
