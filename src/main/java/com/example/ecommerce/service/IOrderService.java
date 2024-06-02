package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.entity.Status;

import java.util.List;

public interface IOrderService {
    OrderDto saveOrUpdate(OrderRequest orderRequest);
    List<OrderDto> records(Status status);

    void approval(Long orderId);
    void updatePayment(Long orderId);
}
