package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.OrderStatus;
import com.example.ecommerce.service.dto.LineItemDto;
import com.example.ecommerce.service.dto.OrderDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("orderMapper")
@RequiredArgsConstructor
public class OrderMapper implements IMapper<Order, OrderRequest, OrderDto> {

    @Qualifier("lineItemMapper")
    private final IMapper<Order.LineItem, OrderRequest.LineItemRequest, LineItemDto> lineItemMapper;

    @Override
    public OrderDto toDto(Order order) {
        OrderDto orderDto = OrderDto.builder()
                .payment(order.getPayment())
                .orderStatus(order.getOrderStatus())
                .approval(order.isApproval())
                .purchased(order.isPurchased())
                .received(order.isReceived())
                .userContactDetails(order.getUserContactDetails())
                .id(order.getId())
                .lineItems(lineItemMapper.toDtoList(order.getLineItems()).stream().collect(Collectors.toSet()))
                .build();
        return orderDto;
    }

    @Override
    public Order toEntity(OrderRequest request) {
        Order order = Order.builder()
                .payment(request.getPayment())
                .userContactDetails(request.getUserContactDetails())
                .orderStatus(OrderStatus.NOT_APPROVAL)
                .lineItems(lineItemMapper.toEntityList(request.getLineItemRequests()).stream().collect(Collectors.toSet()))
                .build();
        return order;
    }

    @Override
    public List<OrderDto> toDtoList(Collection<? extends Order> orders) {
        List<OrderDto> res = new ArrayList<>();
        if(orders != null) {
            orders.forEach(or -> res.add(toDto(or)));
        }
        return res;
    }
}
