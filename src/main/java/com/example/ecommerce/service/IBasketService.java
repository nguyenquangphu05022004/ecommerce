package com.example.ecommerce.service;

import com.example.ecommerce.dto.BasketDto;

import java.util.List;

public interface IBasketService {
    BasketDto saveOrUpdate(BasketDto basketDto);
    List<BasketDto> records();
    void delete(Long id);
    Long count();
}
