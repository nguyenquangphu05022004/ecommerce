package com.example.ecommerce.service;

import com.example.ecommerce.domain.model.binding.CartRequest;
import com.example.ecommerce.domain.model.modelviews.cart.VendorCartUserProfileModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ICartService {
    APIResponse<VendorCartUserProfileModelView> add(CartRequest cartRequest) ;
    APIListResponse<VendorCartUserProfileModelView> getShoppingCart() ;
    APIResponse<VendorCartUserProfileModelView> delete(Long inventory, Long vendorId);
}
