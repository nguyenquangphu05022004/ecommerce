package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.service.dto.StockClassificationDto;
import com.example.ecommerce.service.mapper.IMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("stockClassificationMapper")
public class StockClassificationMapper implements
        IMapper<Stock.StockClassification, Object, StockClassificationDto> {
    @Override
    public Stock.StockClassification toEntity(Object o) {
        StockClassificationDto dto = (StockClassificationDto)o;
        Stock.StockClassification entity = Stock.StockClassification.builder()
                .size(dto.getSizeName())
                .quantityOfProduct(dto.getQuantityOfProduct())
                .seller(0)
                .id(dto.getId())
                .build();
        return entity;
    }

    @Override
    public StockClassificationDto toDto(Stock.StockClassification stockClassification) {
        if(stockClassification == null) return null;
        StockClassificationDto res = StockClassificationDto.builder()
                .id(stockClassification.getId())
                .quantityOfProduct(stockClassification.getQuantityOfProduct())
                .seller(stockClassification.getSeller())
                .sizeName(stockClassification.getSize())
                .build();
        return res;
    }

    @Override
    public List<StockClassificationDto> toDtoList(Collection<? extends Stock.StockClassification> stockClassifications) {
        List<StockClassificationDto> res = new ArrayList<>();
        if(stockClassifications != null || stockClassifications.size() > 0) {
            stockClassifications.forEach(st -> res.add(toDto(st)));
        }
        return res;
    }
}
