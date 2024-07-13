package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.Basket;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.domain.StockClassification;
import com.example.ecommerce.service.dto.BasketDto;
import com.example.ecommerce.service.dto.StockClassificationDto;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.BasketRequest;
import com.example.ecommerce.service.request.StockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@RequiredArgsConstructor
@Service("basketMapper")
public class BasketMapper implements IMapper<Basket, BasketRequest, BasketDto> {
    @Qualifier("stockMapper")
    private final IMapper<Stock, StockRequest, StockDto> stockMapper;
    @Qualifier("stockClassificationMapper")
    private final IMapper<StockClassification, Object, StockClassificationDto> stockClassificationMapper;

    @Override
    public BasketDto toDto(Basket basket) {
        if(basket == null) return null;
        BasketDto basketDto = BasketDto.builder()
                .id(basket.getId())
                .quantity(basket.getQuantity())
                .stock(stockMapper.toDto(basket.getStock()))
                .stockClassification(stockClassificationMapper.toDto(basket.getStockClassification()))
                .build();
        return basketDto;
    }

    @Override
    public List<BasketDto> toDtoList(Collection<? extends Basket> baskets) {
        List<BasketDto> list = new ArrayList<>();
        if(baskets != null) {
            baskets.stream().forEach(b -> list.add(toDto(b)));
        }
        return list;
    }
}
