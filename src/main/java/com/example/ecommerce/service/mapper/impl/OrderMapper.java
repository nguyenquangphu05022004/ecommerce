package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.LineItem;
import com.example.ecommerce.domain.Order;
import com.example.ecommerce.service.dto.LineItemDto;
import com.example.ecommerce.service.dto.OrderDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("orderMapper")
@RequiredArgsConstructor
public class OrderMapper implements IMapper<Order, OrderRequest, OrderDto> {

    @Qualifier("lineItemMapper")
    private final IMapper<LineItem, Object, LineItemDto> lineItemMapper;

    @Override
    public OrderDto toDto(Order order) {
        OrderDto orderDto = OrderDto.builder()
                .build();
        return orderDto;
    }

    @Override
    public Order toEntity(OrderRequest request) {
        Order order = Order.builder()
                .payment(request.getPayment())
                .orderStatus(request.getOrderStatus())
                .userContactDetails(request.getUserContactDetails())
                .build();
        return order;
    }
}
