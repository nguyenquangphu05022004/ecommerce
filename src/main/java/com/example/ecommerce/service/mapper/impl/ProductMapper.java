package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.*;
import com.example.ecommerce.service.dto.EvaluationDto;
import com.example.ecommerce.service.dto.ProductDto;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.dto.VendorDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.EvaluationRequest;
import com.example.ecommerce.service.request.ProductRequest;
import com.example.ecommerce.service.request.StockRequest;
import com.example.ecommerce.service.request.VendorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service("productMapper")
@RequiredArgsConstructor
public class ProductMapper implements IMapper<Product, ProductRequest, ProductDto> {
    @Qualifier("stockMapper")
    private final IMapper<Stock, StockRequest, StockDto> stockMapper;
    @Qualifier("evaluationMapper")
    private final IMapper<Evaluation, EvaluationRequest, EvaluationDto> evaluationMapper;
    @Qualifier("vendorMapper")
    private final IMapper<Vendor, VendorRequest, VendorDto> vendorMapper;

    @Override
    public Product toEntity(ProductRequest request) {
        Product product = Product.builder()
                .description(request.getDescription())
                .language(new Language(request.getName(), request.getNameEn()))
                .category(Category.builder().id(request.getCategoryId()).build())
                .brand(Brand.builder().id(request.getBrandId()).name(request.getBrandName()).build())
                .build();
        return product;
    }

    @Override
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .description(product.getDescription())
                .name(product.getLanguage().getNameVn())
                .categoryName(product.getCategory().getName())
                .id(product.getId())
                .stocks(stockMapper.toDtoList(product.getStocks()))
                .evaluations(evaluationMapper.toDtoList(product.getEvaluations()))
                .vendor(vendorMapper.toDto(product.getVendor()))
                .brandName(product.getBrand().getName())
                .build();
    }

    @Override
    public List<ProductDto> toDtoList(Collection<? extends Product> products) {
        List<ProductDto> results = new ArrayList<>();
        if(products != null) {
            products.forEach(product -> results.add(toDto(product)));
        }
        return results;
    }
}
