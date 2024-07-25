package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.service.dto.LineItemDto;
import com.example.ecommerce.service.dto.StockClassificationDto;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.StockRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("lineItemMapper")
@AllArgsConstructor
public class LineItemMapper implements IMapper<Order.LineItem, Object, LineItemDto> {
    @Qualifier("stockMapper")
    private final IMapper<Stock, StockRequest, StockDto> stockMapper;
    @Qualifier("stockClassificationMapper")
    private final IMapper<Stock.StockClassification, Object, StockClassificationDto> stockClassMapper;
    @Override
    public LineItemDto toDto(Order.LineItem lineItem) {
        LineItemDto line = LineItemDto.builder()
//                .items()
//                .id(lineItem.getId())
//                .createdBy(lineItem.getCreatedBy())
//                .id(lineItem.getId())
                .build();
        return line;
    }

    @Override
    public Order.LineItem toEntity(Object o) {
        Order.LineItem lineItem = null;
        if(o instanceof LineItemDto) {
            LineItemDto line = (LineItemDto) o;
            lineItem = Order.LineItem.builder()
//                    .stock(Stock.builder().id(line.getStock().getId()).build())
//                    .stockClassification(StockClassification.builder().id(line.getStockClassification().getId()).build())
//                    .quantity(line.getQuantity())
                    .build();
        }
        return lineItem;
    }

    @Override
    public List<LineItemDto> toDtoList(Collection<? extends Order.LineItem> lineItems) {
        List<LineItemDto> res = new ArrayList<>();
        if(lineItems != null) {
            lineItems.forEach(item -> res.add(toDto(item)));
        }
        return res;
    }
}
