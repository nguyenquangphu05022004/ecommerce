package com.example.ecommerce.service;


import com.example.ecommerce.domain.model.binding.FilterOrderRequest;
import com.example.ecommerce.domain.model.binding.OrderRequest;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;

public interface IOrderService {
    APIResponse<?> createOrder(OrderRequest request);
    APIListResponse<?> getAllOrderByCustomer(FilterOrderRequest status);
    APIResponse<?> updatePayment(Long orderId);

    APIResponse<?> deleteById(Long orderId);
    APIResponse<?> updateOrderState(Long orderId, boolean isNext);
}
