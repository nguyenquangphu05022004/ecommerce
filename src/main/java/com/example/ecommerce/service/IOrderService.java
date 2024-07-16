package com.example.ecommerce.service;


import com.example.ecommerce.domain.OrderStatus;
import com.example.ecommerce.service.dto.OrderDto;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderDto request);
    List<OrderDto> getAllOrderByCustomer(OrderStatus status);
    void approval(Long orderId, Boolean approval);
    void updatePayment(Long orderId);

    void deleteById(Long orderId);
}
