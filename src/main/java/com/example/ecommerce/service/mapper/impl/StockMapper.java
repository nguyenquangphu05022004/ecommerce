package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.StockRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("stockMapper")
public class StockMapper implements IMapper<Stock, StockRequest, StockDto> {
    @Override
    public Stock toEntity(StockRequest stockRequest) {
        return null;
    }

    @Override
    public StockDto toDto(Stock stock) {
        return null;
    }

    @Override
    public List<StockDto> toDtoList(List<Stock> stocks) {
        return null;
    }
}
