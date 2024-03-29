package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.entity.Status;

import java.util.List;

public interface IOrderService {
    OrderDto saveOrUpdate(OrderDto orderDto);
    List<OrderDto> records(Status status);
}
