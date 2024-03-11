package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.entity.Basket;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class BasketConverterImpl implements IGenericConverter<Basket, BasketDto> {
    private final ModelMapper mapper;

    public BasketConverterImpl(ModelMapper mapper) {
        this.mapper = mapper;
//        skipProperty();
    }

//    private void skipProperty() {
//        TypeMap<Basket, BasketDto> typeMap = this.mapper.createTypeMap(Basket.class, BasketDto.class);
//        typeMap.addMappings(map -> map.skip(Basket::getUser, BasketDto::setUser));
//    }

    @Override
    public Basket toEntity(BasketDto basketDto) {
        Basket basket = mapper.map(basketDto, Basket.class);
        return basket;
    }

    @Override
    public BasketDto toDto(Basket basket) {
        BasketDto basketDto = mapper.map(basket, BasketDto.class);
        basketDto.setTotalPrice(basket.getTotalPrice());
        return basketDto;
    }

    @Override
    public Basket toEntity(Basket basket, BasketDto basketDto) {
        return null;
    }
}
