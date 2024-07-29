package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.service.dto.LineItemDto;
import com.example.ecommerce.service.dto.StockClassificationDto;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.OrderRequest;
import com.example.ecommerce.service.request.StockRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("itemMapper")
@AllArgsConstructor
public class ItemMapper implements IMapper<Order.Item, OrderRequest.ItemRequest, LineItemDto.ItemDto> {
    @Qualifier("stockMapper")
    private final IMapper<Stock, StockRequest, StockDto> stockMapper;
    @Qualifier("stockClassificationMapper")
    private final IMapper<Stock.StockClassification, Object, StockClassificationDto> stockClassMapper;
    @Override
    public LineItemDto.ItemDto toDto(Order.Item item) {
        return LineItemDto.ItemDto.builder()
                .id(item.getId())
                .stock(stockMapper.toDto(item.getStock()))
                .stockClassification(stockClassMapper.toDto(item.getStockClassification()))
                .quantity(item.getQuantity())
                .build();
    }

    @Override
    public Order.Item toEntity(OrderRequest.ItemRequest request) {
        return Order.Item.builder()
                .stockClassification(Stock.StockClassification.builder().id(request.getClassificationId()).build())
                .stock(Stock.builder().id(request.getStockId()).build())
                .quantity(request.getQuantity())
                .build();
    }

    @Override
    public List<LineItemDto.ItemDto> toDtoList(Collection<? extends Order.Item> items) {
        List<LineItemDto.ItemDto> res = new ArrayList<>();
        if(items != null) {
            items.forEach(e -> res.add(toDto(e)));
        }
        return res;
    }
}
