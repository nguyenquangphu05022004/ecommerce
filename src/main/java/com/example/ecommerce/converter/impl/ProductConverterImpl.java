package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.EvaluationDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.VendorDto;
import com.example.ecommerce.entity.Language;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Vendor;
import com.example.ecommerce.utils.Convert;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
        Language language = product.getLanguage()
                .toBuilder().product(null).build();
        Vendor vendor = product.getVendor()
                .toBuilder()
                .products(null)
                .user(null)
                .build();
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .language(language.toBuilder().product(null).build())
                .category((CategoryDto) Convert.CATE.toDto(product.getCategory()))
                .thumbnail(product.getThumbnail())
                .quantity(product.getQuantity())
                .evaluations(product.getEvaluations().stream()
                        .map(evaluation ->
                                (EvaluationDto)Convert.EVAL.toDto(evaluation))
                        .collect(Collectors.toList())
                )
                .vendor((VendorDto) Convert.VEND.toDto(vendor))
                .build();
        return productDto;
    }

    @Override
    public Product toEntity(Product product, ProductDto productDto) {
        return null;
    }

}
