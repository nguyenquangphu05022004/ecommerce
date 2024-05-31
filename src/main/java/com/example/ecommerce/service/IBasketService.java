package com.example.ecommerce.service;

import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.dto.BasketRequest;

import java.util.List;

public interface IBasketService {
    void saveOrUpdate(BasketRequest basketRequest);
    List<BasketDto> records();
    void delete(Long id);
    Long count();
}
