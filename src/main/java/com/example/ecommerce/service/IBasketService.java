package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.product.BasketDto;
import com.example.ecommerce.domain.dto.product.BasketRequest;

import java.util.List;

public interface IBasketService {
    BasketDto saveOrUpdate(BasketRequest basketRequest);
    List<BasketDto> records();
    void delete(Long id);
    Long count();
}
