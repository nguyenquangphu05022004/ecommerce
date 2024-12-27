package com.example.ecommerce.promotion.dal.dataobject.discount;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.promotion.dal.enums.PromotionDiscountTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "promotion_discount")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Discount extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_spu_id")
    private ProductSpu productSpu;

    @Enumerated(EnumType.STRING)
    private PromotionDiscountTypeEnum discountType;

    private Integer discountAmount;

    @ManyToOne
    @JoinColumn(name = "discount_activity_id")
    private DiscountActivity discountActivity;

}
