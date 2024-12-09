package com.example.ecommerce.promotion.dal.dataobject.coupon;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.promotion.dal.enums.CommonStatusTypeEnum;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "promotion_user_coupon")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserCoupon extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember userMember;

    @Enumerated(EnumType.STRING)
    private CommonStatusTypeEnum status;
}
