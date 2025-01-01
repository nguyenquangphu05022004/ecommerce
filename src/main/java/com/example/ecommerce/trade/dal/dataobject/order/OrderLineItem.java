package com.example.ecommerce.trade.dal.dataobject.order;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "trade_order_order_line_item")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Setter
@Getter
public class OrderLineItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @OneToMany(mappedBy = "orderLineItem")
    private List<OrderItem> items;
    private Boolean commentStatus;

    private Boolean orderIsGranted;
    private Boolean itemsAreDeliveredToWareHouse;

    @Transient
    public String itemsName() {
        return StringUtils.convertToString(items, item -> item.getProductSku().getProductSpu().getName(), ", ");
    }
}
