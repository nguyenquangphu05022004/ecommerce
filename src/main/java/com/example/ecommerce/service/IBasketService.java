package com.example.ecommerce.service;

import java.util.List;

public interface IBasketService {
    void saveOrUpdate(BasketRequest basketRequest);
    List<BasketDto> findAllByUser();
    void delete(Long id);
    Long count();
}
