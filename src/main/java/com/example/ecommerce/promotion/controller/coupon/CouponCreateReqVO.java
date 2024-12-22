package com.example.ecommerce.promotion.controller.coupon;

import com.example.ecommerce.promotion.dal.enums.PromotionCouponScopeTypeEnum;
import com.example.ecommerce.promotion.dal.enums.PromotionProductScopeEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CouponCreateReqVO {
    private String description;
    private Long ownerId;
    private String code;

    private PromotionProductScopeEnum productScope;

    private Integer limitMinPrice;
    private Integer limitMaxPrice;

    private LocalDateTime begin;
    private LocalDateTime end;
    private PromotionCouponScopeTypeEnum couponScope;

}
