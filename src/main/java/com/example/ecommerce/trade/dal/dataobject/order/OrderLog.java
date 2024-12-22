package com.example.ecommerce.trade.dal.dataobject.order;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.trade.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
