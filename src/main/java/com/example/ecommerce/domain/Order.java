package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class Order extends BaseEntity {

    @Embedded
    private UserContactDetails userContactDetails;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<LineItem> lineItems;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private boolean approval;
    private boolean purchased;
    private boolean received;
}
