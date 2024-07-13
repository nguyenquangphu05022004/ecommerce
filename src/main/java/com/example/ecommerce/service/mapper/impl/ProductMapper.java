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
    public Product toEntity(ProductRequest productRequest) {
        Product product = Product.builder()
                .description(productRequest.getDescription())
                .language(new Language(productRequest.getName(), productRequest.getNameEn()))
                .category(Category.builder().id(productRequest.getCategoryId()).build())
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
