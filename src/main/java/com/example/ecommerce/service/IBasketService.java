package com.example.ecommerce.service;

import com.example.ecommerce.service.dto.BasketDto;
import com.example.ecommerce.service.request.BasketRequest;

import java.util.List;

public interface IBasketService {
    void saveOrUpdate(BasketRequest basketRequest);
    List<BasketDto> findAllByUser();
    void delete(Long id);
    Long count();
}
