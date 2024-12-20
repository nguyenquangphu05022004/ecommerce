package com.example.ecommerce.trade.dal.dataobject.order;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.beans.Transient;
import java.util.List;
import java.util.Set;

@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "trade_order")
@Getter
@Setter
@NoArgsConstructor
public class Order extends BaseEntity {
    //System.getCurrentTime()...
    private String no;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderLineItem> lineItems;

    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember userMember;

    private String addressDetails;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    public Integer totalPrice() {
        return 0;
    }

    public Integer totalProduct() {
        return 0;
    }
}
