package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Product;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ProductConverterImpl implements IGenericConverter<Product, ProductDto> {
    private final ModelMapper mapper;
    public ProductConverterImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        return mapper.map(productDto, Product.class);
    }

    @Override
    public ProductDto toDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public Product toEntity(Product product, ProductDto productDto) {
        return null;
    }
}
