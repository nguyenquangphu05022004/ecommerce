package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.utils.Convert;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class BasketConverterImpl implements IGenericConverter<Basket, BasketDto> {
    private final ModelMapper mapper;

    public BasketConverterImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Basket toEntity(BasketDto basketDto) {
        Basket basket = mapper.map(basketDto, Basket.class);
        return basket;
    }

    @Override
    public BasketDto toDto(Basket basket) {
        BasketDto basketDto = BasketDto.builder()
                .id(basket.getId())
                .quantity(basket.getQuantity())
                .product((ProductDto) Convert.PRO.toDto(basket.getProduct()))
                .user((UserDto) Convert.USER.toDto(basket.getUser()))
                .build();
        basketDto.setTotalPrice(basket.getTotalPrice());
        return basketDto;
    }

    @Override
    public Basket toEntity(Basket basket, BasketDto basketDto) {
        return null;
    }
}
