package com.example.ecommerce.service;


import com.example.ecommerce.domain.entities.order.OrderStatus;
import com.example.ecommerce.service.dto.OrderDto;
import com.example.ecommerce.service.request.OrderRequest;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequest request);
    List<OrderDto> getAllOrderByCustomer(OrderStatus status);
    void approval(Long orderId, Boolean approval);
    void updatePayment(Long orderId);

    void deleteById(Long orderId);
}
