package com.example.ecommerce.promotion.dal.dataobject.coupon;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.promotion.dal.enums.PromotionCouponScopeTypeEnum;
import com.example.ecommerce.promotion.dal.enums.PromotionProductScopeEnum;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "promotion_coupon")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@Setter
public class Coupon extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Seller owner;

    private String description;

    @Column(unique = true)
    private String code;
    @Enumerated(EnumType.STRING)
    private PromotionProductScopeEnum productScope;

    private Integer limitMinPrice;
    private Integer limitMaxPrice;

    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private PromotionCouponScopeTypeEnum couponScope;

    /**
     * Dem so luot su dung phieu giam gia
     */
    private Integer countNumber;


    public void increment() {
        if(countNumber == null) {
            countNumber = 0;
        }
        this.countNumber ++;
    }

    public void decrement() {
        if(countNumber == null || countNumber == 0) {
            return;
        }
        this.countNumber --;
    }

}
