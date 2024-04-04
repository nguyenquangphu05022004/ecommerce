package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.utils.Convert;
import org.modelmapper.ModelMapper;

public class BasketConverterImpl implements IGenericConverter<Basket, BasketDto> {

    @Override
    public Basket toEntity(BasketDto basketDto) {
        Basket basket = Basket.builder()
                .quantity(basketDto.getQuantity())
                .product(Product.builder()
                        .id(basketDto.getProduct()
                                .getId()).build())
                .build();
        return basket;
    }

    @Override
    public BasketDto toDto(Basket basket) {
        BasketDto basketDto = BasketDto.builder()
                .id(basket.getId())
                .quantity(basket.getQuantity())
                .product((ProductDto) Convert.PRO.toDto(basket.getProduct()))
                .build();
        basketDto.setTotalPrice(basket.getTotalPrice());
        return basketDto;
    }

    @Override
    public Basket toEntity(Basket basket, BasketDto basketDto) {
        return null;
    }
}
