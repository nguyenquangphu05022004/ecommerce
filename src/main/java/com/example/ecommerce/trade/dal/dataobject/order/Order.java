package com.example.ecommerce.trade.dal.dataobject.order;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.beans.Transient;
import java.util.Set;

public class Order extends BaseEntity {
    //System.getCurrentTime()...
    private String no;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //createdDate -> createdDate
    //cancelDate -> modifiedDate
    private Set<OrderItem> orderItems;

    private UserMember userMember;

    /**
     * Moi user chi co the comment product 1 lan khi
     * mua san pham do
     */
    private Boolean commentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Transient
    public Integer totalProduct() {
        if(CollUtils.isEmpty(this.orderItems)) return 0;
        return this.orderItems.stream().mapToInt(s -> s.getQuantity()).sum();
    }
    @Transient
    public Integer totalPrice() {
        if(CollUtils.isEmpty(this.orderItems)) return 0;
        return this.orderItems.stream().mapToInt(s -> s.totalPrice()).sum();
    }

}
