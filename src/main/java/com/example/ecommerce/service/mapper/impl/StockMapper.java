package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.service.dto.*;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.mapper.ImageMapper;
import com.example.ecommerce.service.request.CategoryRequest;
import com.example.ecommerce.service.request.StockRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service("stockMapper")
@AllArgsConstructor
public class StockMapper implements ImageMapper , IMapper<Stock, StockRequest, StockDto> {
    @Qualifier("stockClassificationMapper")
    private final IMapper<Stock.StockClassification, Object, StockClassificationDto> stockClassificationMapper;
    @Qualifier("categoryMapper")
    private final IMapper<Category, CategoryRequest, CategoryDto> cateMapper;
    @Override
    public Stock toEntity(StockRequest stockRequest) {
        Stock stock = Stock.builder()
                .code(stockRequest.getCode())
                .color(stockRequest.getColor())
                .product(Product.builder().id(stockRequest.getProductId()).build())
                .price(stockRequest.getPrice())
                .build();
        return stock;
    }

    @Override
    public StockDto toDto(Stock stock) {
        StockDto st = StockDto.builder()
                .price(stock.getPrice())
                .code(stock.getCode())
                .color(stock.getColor())
                .imageUrls(getImageUrl(FileEntityType.PRODUCT.name(), stock.getImages()))
                .product(ProductBaseDto.builder()
                        .category(cateMapper.toDto(stock.getProduct().getCategory()))
                        .brand(ProductMapper.brandMapper(stock.getProduct().getProductBrand()))
                        .id(stock.getProduct().getId())
                        .name(stock.getProduct().getLanguage().getNameVn())
                        .description(stock.getProduct().getDescription())
                        .build())
                .id(stock.getId())
                .stockClassifications(stockClassificationMapper.toDtoList(stock.getStockClassifications()))
                .build();
        return st;
    }

    @Override
    public List<StockDto> toDtoList(Collection<? extends Stock> stocks) {
        List<StockDto> res = new ArrayList<>();
        if(stocks != null || stocks.size() > 0) {
            stocks.forEach(st -> res.add(toDto(st)));
        }
        return res;
    }
}
