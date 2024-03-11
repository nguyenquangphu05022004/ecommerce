package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.entity.Order;
import org.modelmapper.ModelMapper;

public class OrderConverter implements IGenericConverter<Order, OrderDto> {
    private final ModelMapper mapper;

    public OrderConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public Order toEntity(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto toDto(Order order) {
        OrderDto orderDto = mapper.map(order, OrderDto.class);
        orderDto.setTotalPrice(order.getTotalPrice());
        return orderDto;
    }

    @Override
    public Order toEntity(Order order, OrderDto orderDto) {
        return null;
    }
}
