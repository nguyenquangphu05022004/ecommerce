package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.*;
import com.example.ecommerce.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter implements IGenericConverter<Order, OrderDto> {


    @Override
    public Order toEntity(OrderDto orderDto) {
        Order order = Order.builder()
                .payment(orderDto.getPayment())
                .quantity(orderDto.getQuantity())
                .couponPercent(0)
                .build();
        return order;
    }
    @Override
    public OrderDto toDto(Order order) {
        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .payment(order.getPayment())
                .quantity(order.getQuantity())
                .contactDetails(order.getUser().getUserContactDetails())
                .build();
        return orderDto;
    }

    @Override
    public Order toEntity(Order order, OrderDto orderDto) {
        return null;
    }
}
