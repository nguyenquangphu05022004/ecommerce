package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.StockClassification;
import com.example.ecommerce.service.dto.StockClassificationDto;
import com.example.ecommerce.service.mapper.IMapper;
import org.springframework.stereotype.Service;
@Service("stockClassificationMapper")
public class StockClassificationMapper implements
        IMapper<StockClassification, Object, StockClassificationDto> {
    @Override
    public StockClassificationDto toDto(StockClassification stockClassification) {
        if(stockClassification == null) return null;
        StockClassificationDto res = StockClassificationDto.builder()
                .id(stockClassification.getId())
                .quantityOfProduct(stockClassification.getQuantityOfProduct())
                .seller(stockClassification.getSeller())
                .sizeName(stockClassification.getSize().getName())
                .build();
        return res;
    }

}
