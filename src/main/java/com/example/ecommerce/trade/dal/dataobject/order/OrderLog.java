package com.example.ecommerce.trade.dal.dataobject.order;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

@Entity
@Table(name = "trade_order_log")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class OrderLog extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Enumerated(EnumType.STRING)
    private OrderStatus previousStatus;
    @Enumerated(EnumType.STRING)
    private OrderStatus nextStatus;

    private String content;
}
