package com.example.ecommerce.domain.entities;

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
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    private String address;
    private String province;
    private String district;
    private String ward;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
}
