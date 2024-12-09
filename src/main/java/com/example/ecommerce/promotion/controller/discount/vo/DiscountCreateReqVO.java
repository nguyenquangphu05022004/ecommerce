package com.example.ecommerce.promotion.controller.discount.vo;

import com.example.ecommerce.promotion.dal.enums.PromotionDiscountTypeEnum;
import lombok.Data;

@Data
public class DiscountCreateReqVO {
    private Long productSpuId;

    private PromotionDiscountTypeEnum discountType;

    private Integer discountPrice;
    private Integer discountPercent;

    private Long discountActivityId;
}
