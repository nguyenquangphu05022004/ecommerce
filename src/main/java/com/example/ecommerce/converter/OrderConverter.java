package com.example.ecommerce.converter;

import com.example.ecommerce.domain.dto.product.OrderDto;
import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.dto.product.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final ModelMapper mapper;
    public Order toEntity(OrderRequest orderRequest) {
        Order order = mapper.map(orderRequest, Order.class);
        return order;
    }
    public OrderDto toDto(Order order) {
        OrderDto orderDto = mapper.map(order, OrderDto.class);
        return orderDto;
    }

}
