package com.example.ecommerce.promotion.dal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PromotionDiscountTypeEnum {
    PERCENT("Tinh theo %"),
    PRICE("Tinh theo gia");

    @Getter
    private final String name;

}
