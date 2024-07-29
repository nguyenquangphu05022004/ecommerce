package com.example.ecommerce.domain.entities.order;

import com.example.ecommerce.domain.OrderStatus;
import com.example.ecommerce.domain.UserContactDetails;
import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.auth.User;
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
public class Order extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Embedded
    private UserContactDetails userContactDetails;

    @OneToMany(mappedBy = "order")
    private Set<LineItem> lineItems;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private boolean approval;
    private boolean purchased;
    private boolean received;

}
