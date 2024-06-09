package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.BillDto;
import com.example.ecommerce.domain.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillConverterImpl implements IGenericConverter<Bill, BillDto> {
    private final OrderConverter orderConverter;

    @Autowired
    public BillConverterImpl(OrderConverter orderConverter) {
        this.orderConverter = orderConverter;
    }

    @Override
    public Bill toEntity(BillDto billDto) {
            return null;
    }

    @Override
    public BillDto toDto(Bill bill) {
        BillDto billDto = BillDto.builder()
                .id(bill.getId())
                .name(bill.getName())
                .order(orderConverter.toDto(bill.getOrder()))
                .status(bill.getStatus())
                .build();
        return billDto;
    }

    @Override
    public Bill toEntity(Bill bill, BillDto billDto) {
        return null;
    }
}
