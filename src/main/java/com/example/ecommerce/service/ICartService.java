package com.example.ecommerce.service;

import com.example.ecommerce.service.request.CartRequest;
import com.example.ecommerce.service.response.ShoppingCartResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ICartService {
    void add(CartRequest cartRequest) ;
    ShoppingCartResponse getShoppingCart() ;
    void delete(Long stockId);
}
