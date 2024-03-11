package com.example.ecommerce.service;

import com.example.ecommerce.dto.BasketDto;

public interface IBasketService {
    BasketDto saveOrUpdate(BasketDto basketDto);
}
