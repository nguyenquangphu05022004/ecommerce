package com.example.ecommerce.promotion.controller.admin.coupon.vo;

import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;
import com.example.ecommerce.promotion.dal.enums.PromotionCouponScopeTypeEnum;
import com.example.ecommerce.promotion.dal.enums.PromotionProductScopeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class CouponRespVO {
    private Long id;
    private String description;
    private String code;
    private PromotionProductScopeEnum productScope;

    private Integer limitMinPrice;
    private Integer limitMaxPrice;

    private LocalDateTime begin;
    private LocalDateTime end;

    private PromotionCouponScopeTypeEnum couponScope;

    private Integer countNumber;

    public CouponRespVO(Coupon c) {
        id = c.getId(); description = c.getDescription(); code = c.getCode();
        productScope = c.getProductScope(); limitMinPrice = c.getLimitMinPrice();
        limitMaxPrice = c.getLimitMinPrice(); begin = c.getBeginDate(); end = c.getEndDate();
        couponScope = c.getCouponScope(); countNumber = c.getCountNumber();
    }
}
