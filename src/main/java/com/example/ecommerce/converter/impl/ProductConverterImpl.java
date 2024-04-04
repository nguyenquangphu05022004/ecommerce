package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.utils.Convert;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductConverterImpl implements IGenericConverter<Product, ProductDto> {
    private final ModelMapper mapper;

    public ProductConverterImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .language(new Language(productDto.getLanguage().getNameVn(), productDto.getLanguage().getNameEn()))
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .category(Category.builder().id(productDto.getCategory().getId()).build())
                .description(productDto.getDescription()).build();
    }

    @Override
    public ProductDto toDto(Product product) {
        Vendor vendor = product.getVendor().toBuilder().products(null).user(null).build();

        TrackProductSeller productSeller = product.getProductSeller() != null ?
                TrackProductSeller.builder().numberOfProductsSold(product.getProductSeller().getNumberOfProductsSold()).build() : null;

        List<ImageDto> thumbnails = product.getThumbnails() != null ?
                product.getThumbnails().stream().map(e -> new ImageDto(e.getName(), e.getShortUrl())).collect(Collectors.toList()) : new ArrayList<>();

        List<EvaluationDto> evaluations = product.getEvaluations() != null ?
                product.getEvaluations().stream().map(e -> (EvaluationDto) Convert.EVAL.toDto(e)).collect(Collectors.toList()) : new ArrayList<>();

        LanguageDto language = LanguageDto.builder().nameEn(product.getLanguage().getNameEn()).nameVn(product.getLanguage().getNameVn()).build();

        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .language(language)
                .category((CategoryDto) Convert.CATE.toDto(product.getCategory()))
                .thumbnails(thumbnails)
                .quantity(product.getQuantity())
                .evaluations(evaluations)
                .trackProductSeller(productSeller)
                .vendor((VendorDto) Convert.VEND.toDto(vendor))
                .build();
        return productDto;
    }

    @Override
    public Product toEntity(Product product, ProductDto productDto) {
        return null;
    }

}
