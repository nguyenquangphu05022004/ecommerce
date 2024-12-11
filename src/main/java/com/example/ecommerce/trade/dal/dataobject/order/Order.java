package com.example.ecommerce.trade.dal.dataobject.order;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.beans.Transient;
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

    //createdDate -> createdDate
    //cancelDate -> modifiedDate
    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember userMember;

    private String address;

    /**
     * Moi user chi co the comment product 1 lan khi
     * mua san pham do
     */
    private Boolean commentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    @Transient
    @JsonIgnore
    public Integer totalProduct() {
        if(CollUtils.isEmpty(this.orderItems)) return 0;
        return this.orderItems.stream().mapToInt(s -> s.getQuantity()).sum();
    }
    @Transient
    @JsonIgnore
    public Integer totalPrice() {
        if(CollUtils.isEmpty(this.orderItems)) return 0;
        return this.orderItems.stream().mapToInt(s -> s.totalPrice()).sum();
    }

}
