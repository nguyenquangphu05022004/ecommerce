package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.entity.Order;

public class OrderConverter implements IGenericConverter<Order, OrderDto> {
    @Override
    public Order toEntity(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto toDto(Order order) {
        return null;
    }

    @Override
    public Order toEntity(Order order, OrderDto orderDto) {
        return null;
    }
}
