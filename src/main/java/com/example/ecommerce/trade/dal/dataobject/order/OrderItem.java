package com.example.ecommerce.trade.dal.dataobject.order;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "trade_order_item")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name  = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_sku_id")
    private ProductSku productSku;

    private Integer quantity;

    @Transient
    public Integer totalPrice() {
        return productSku.getPrice() * quantity;
    }
}
