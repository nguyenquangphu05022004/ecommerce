package com.example.ecommerce.service;

import com.example.ecommerce.dto.StockRequest;
import com.example.ecommerce.dto.StockResponse;

public interface IStockService extends IGenericService<StockResponse> {
    void save(StockRequest stockRequest);
    void update(Long stockId);
}
