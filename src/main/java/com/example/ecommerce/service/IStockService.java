package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.product.StockRequest;
import com.example.ecommerce.domain.dto.product.StockResponse;

public interface IStockService extends IGenericService<StockResponse> {
    void save(StockRequest stockRequest);
    void update(Long stockId);
}
