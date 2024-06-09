package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.domain.Basket;

public class BasketConverterImpl implements IGenericConverter<Basket, BasketDto> {

    @Override
    public Basket toEntity(BasketDto basketDto) {
        return null;
    }

    @Override
    public BasketDto toDto(Basket basket) {
       return null;
    }

    @Override
    public Basket toEntity(Basket basket, BasketDto basketDto) {
        return null;
    }
}
