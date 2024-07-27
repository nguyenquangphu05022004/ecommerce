package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.Coupon;
import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.service.dto.LineItemDto;
import com.example.ecommerce.service.dto.StockClassificationDto;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.OrderRequest;
import com.example.ecommerce.service.request.StockRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("lineItemMapper")
@AllArgsConstructor
public class LineItemMapper implements IMapper<Order.LineItem, OrderRequest.LineItemRequest, LineItemDto> {
    @Qualifier("itemMapper")
    private final IMapper<Order.Item, OrderRequest.ItemRequest, LineItemDto.ItemDto> itemMapper;

    @Override
    public LineItemDto toDto(Order.LineItem lineItem) {
        LineItemDto line = LineItemDto.builder()
                .items(itemMapper.toDtoList(lineItem.getItems()).stream().collect(Collectors.toSet()))
                .id(lineItem.getId())
                .createdBy(lineItem.getCreatedBy())
                .id(lineItem.getId())
                .build();
        return line;
    }

    @Override
    public Order.LineItem toEntity(OrderRequest.LineItemRequest request) {
        Order.LineItem lineItem = Order.LineItem.builder()
                .coupon(request.getCouponId() != null ? Coupon.builder().id(request.getCouponId()).build() : null)
                .vendor(Vendor.builder().id(request.getVendorId()).build())
                .items(request.getItemRequests().stream()
                        .map(r -> Order.Item.builder()
                                .stock(Stock.builder().id(r.getStockId()).build())
                                .stockClassification(Stock.StockClassification.builder().id(r.getClassificationId()).build())
                                .quantity(r.getQuantity())
                                .build()).collect(Collectors.toSet()))
                .build();
        return lineItem;
    }

    @Override
    public List<LineItemDto> toDtoList(Collection<? extends Order.LineItem> lineItems) {
        List<LineItemDto> res = new ArrayList<>();
        if (lineItems != null) {
            lineItems.forEach(item -> res.add(toDto(item)));
        }
        return res;
    }

    @Override
    public List<Order.LineItem> toEntityList(Collection<? extends OrderRequest.LineItemRequest> entities) {
        List<Order.LineItem> lineItems = new ArrayList<>();
        if(entities != null) {
            entities.forEach(e -> lineItems.add(toEntity(e)));
        }
        return lineItems;
    }
}
