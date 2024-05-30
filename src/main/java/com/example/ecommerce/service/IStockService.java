package com.example.ecommerce.service;

import com.example.ecommerce.dto.StockDto;
import com.example.ecommerce.dto.StockRequest;

public interface IStockService extends IGenericService<StockDto> {
    void save(StockRequest stockRequest);
    void update(Long stockId);
}
