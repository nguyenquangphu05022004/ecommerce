package com.example.ecommerce.service;

import com.example.ecommerce.service.request.CartRequest;
import com.example.ecommerce.service.response.ShoppingCartResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface ICartService {
    void add(CartRequest cartRequest, HttpServletRequest servletRequest) ;
    ShoppingCartResponse getShoppingCart(HttpServletRequest servletRequest) ;
    void delete(Long stockId, Long vendorId, HttpServletRequest servletRequest);
}
