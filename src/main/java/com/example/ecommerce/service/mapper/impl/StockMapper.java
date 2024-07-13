package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.EntityType;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.domain.StockClassification;
import com.example.ecommerce.service.dto.StockClassificationDto;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.mapper.ImageMapper;
import com.example.ecommerce.service.request.StockRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service("stockMapper")
@AllArgsConstructor
public class StockMapper extends ImageMapper implements IMapper<Stock, StockRequest, StockDto> {
    @Qualifier("stockClassificationMapper")
    private final IMapper<StockClassification, Object, StockClassificationDto> stockClassificationMapper;
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
                .imageUrls(getImageUrl(EntityType.PRODUCT.name(), stock.getImages()))
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
