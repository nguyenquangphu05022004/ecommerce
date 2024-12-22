package com.example.ecommerce.promotion.dal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PromotionCouponScopeTypeEnum {
    ALL("Tat ca nguoi dung"),
    USER("Nguoi dung chi dinh");
    @Getter
    private final String name;
}
