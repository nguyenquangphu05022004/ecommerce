package com.example.ecommerce.domain.entities.auth;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vendors")
@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Vendor extends BaseEntity {

    @Column(columnDefinition = "nvarchar(100)",nullable = false)
    private String shopName;

    private Integer perMoneyDelivery;

    @OneToMany(mappedBy = "vendor")
    private List<Product> products;

    @ManyToMany
    @JoinTable(name = "favorite_vendor", joinColumns = @JoinColumn(name = "vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;
    public Vendor(Long id) {super(id);}

}
