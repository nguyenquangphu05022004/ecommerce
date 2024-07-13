package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.StockClassification;
import com.example.ecommerce.service.dto.StockClassificationDto;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.mapper.IMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("stockClassificationMapper")
public class StockClassificationMapper implements
        IMapper<StockClassification, Object, StockClassificationDto> {
    @Override
    public StockClassification toEntity(Object o) {
        StockClassificationDto dto = (StockClassificationDto)o;
        StockClassification entity = StockClassification.builder()
                .size(dto.getSizeName())
                .quantityOfProduct(dto.getQuantityOfProduct())
                .seller(0)
                .id(dto.getId())
                .build();
        return entity;
    }

    @Override
    public StockClassificationDto toDto(StockClassification stockClassification) {
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
    public List<StockClassificationDto> toDtoList(Collection<? extends StockClassification> stockClassifications) {
        List<StockClassificationDto> res = new ArrayList<>();
        if(stockClassifications != null || stockClassifications.size() > 0) {
            stockClassifications.forEach(st -> res.add(toDto(st)));
        }
        return res;
    }
}
