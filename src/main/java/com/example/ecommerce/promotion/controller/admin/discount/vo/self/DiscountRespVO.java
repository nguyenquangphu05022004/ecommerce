package com.example.ecommerce.promotion.controller.admin.discount.vo.self;

import com.example.ecommerce.promotion.controller.admin.discount.vo.activity.DiscountActivityRespVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;
import com.example.ecommerce.promotion.dal.enums.PromotionDiscountTypeEnum;
import lombok.Data;

@Data
public class DiscountRespVO {
    private Long id;
    private PromotionDiscountTypeEnum discountType;
    private Integer discountAmount;
    private Long productSpuId;
    private DiscountActivityRespVO discountActivity;

    public DiscountRespVO(Discount discount) {
        this.id = discount.getId();
        this.discountType = discount.getDiscountType();
        this.discountAmount = discount.getDiscountAmount();
        this.productSpuId = discount.getProductSpu().getId();
        this.discountActivity = new DiscountActivityRespVO(discount.getDiscountActivity());
    }
}
