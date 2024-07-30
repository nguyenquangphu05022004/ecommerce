package com.example.ecommerce.domain.entities.auth;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Customer extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
}
