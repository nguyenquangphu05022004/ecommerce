package com.example.ecommerce.promotion.controller.admin.discount.vo.self;

import com.example.ecommerce.promotion.dal.enums.PromotionDiscountTypeEnum;
import lombok.Data;

@Data
public class DiscountCreateReqVO {
    private Long id;
    private Long discountActivityId;
    private PromotionDiscountTypeEnum discountType;
    private Integer discountAmount;
    private Long productSpuId;
}
