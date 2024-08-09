package com.example.ecommerce.service;

import com.example.ecommerce.domain.model.binding.CartRequest;
import com.example.ecommerce.domain.model.modelviews.cart.VendorCartModelView;
import com.example.ecommerce.service.response.ShoppingCartResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ICartService {
    void add(CartRequest cartRequest, HttpServletRequest servletRequest) ;
    List<VendorCartModelView> getShoppingCart(HttpServletRequest servletRequest) ;
    void delete(Long inventory, Long vendorId, HttpServletRequest servletRequest);
}
