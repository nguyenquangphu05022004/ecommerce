package com.example.ecommerce.trade.dal.dataobject.cart;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

@Entity
@Table(name = "trade_cart")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class Cart extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember userMember;

    @ManyToOne
    @JoinColumn(name = "product_sku_id")
    private ProductSku productSku;

    private Integer quantity;
    private Boolean selected;


    @Transient
    public Integer totalPrice() {
        return quantity * productSku.getPrice();
    }
}
