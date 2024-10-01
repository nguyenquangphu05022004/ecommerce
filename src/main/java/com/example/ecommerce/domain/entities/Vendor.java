package com.example.ecommerce.domain.entities;

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
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@DiscriminatorValue("VENDOR")
public class Vendor extends User {

    @Column(columnDefinition = "nvarchar(100)",nullable = false)
    private String shopName;

    @OneToMany(mappedBy = "vendor", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Product> products;


    @ManyToMany
    @JoinTable(name = "favorite_vendor", joinColumns = @JoinColumn(name = "vendor_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> usersFavorite;

    public String getShopName() {
        return shopName;
    }

    public List<Product> getProducts() {
        if(products == null) products = new ArrayList<>();
        return products;
    }

    public Set<User> getUsersFavorite() {
        return usersFavorite;
    }
}
