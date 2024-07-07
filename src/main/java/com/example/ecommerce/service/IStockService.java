package com.example.ecommerce.service;


import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.request.StockRequest;

public interface IStockService  {
    void save(StockRequest stockRequest);
    void delete(Long id);
    StockDto findById(Long id);
}
