package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.BillDto;
import com.example.ecommerce.entity.Bill;

public class BillConverterImpl implements IGenericConverter<Bill, BillDto> {

    @Override
    public Bill toEntity(BillDto billDto) {
            return null;
    }

    @Override
    public BillDto toDto(Bill bill) {
        return null;
    }

    @Override
    public Bill toEntity(Bill bill, BillDto billDto) {
        return null;
    }
}
