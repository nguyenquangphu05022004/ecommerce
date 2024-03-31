package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.BillDto;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.entity.Bill;
import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.utils.Convert;

public class BillConverterImpl implements IGenericConverter<Bill, BillDto> {

    @Override
    public Bill toEntity(BillDto billDto) {
            return null;
    }

    @Override
    public BillDto toDto(Bill bill) {
        BillDto billDto = BillDto.builder()
                .id(bill.getId())
                .name(bill.getName())
                .order((OrderDto) Convert.ORDER.toDto(bill.getOrder()))
                .status(bill.getStatus())
                .build();
        return billDto;
    }

    @Override
    public Bill toEntity(Bill bill, BillDto billDto) {
        return null;
    }
}
