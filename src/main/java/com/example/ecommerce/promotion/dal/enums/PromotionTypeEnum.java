package com.example.ecommerce.promotion.dal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PromotionTypeEnum {
    COUPON("Phieu giam gia"),
    DISCOUNT_ACTIVITY("Hoat dong giam gia"),
    REWARD_ACTIVITY("Nhan phieu giam gia khi mua hang");

    @Getter
    private final String name;
}
