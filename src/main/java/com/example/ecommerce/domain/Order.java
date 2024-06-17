package com.example.ecommerce.domain;

import com.example.ecommerce.domain.dto.ENUM.Payment;
import com.example.ecommerce.domain.dto.ENUM.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class Order extends Base{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean approval;
    private boolean purchased;
    private boolean received;
}
