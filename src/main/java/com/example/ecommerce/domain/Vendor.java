package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendors")
@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class Vendor extends BaseEntity {
    @Column(columnDefinition = "nvarchar(100)",nullable = false)
    private String shopName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer perMoneyDelivery;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_favorite_id")
    private VendorFavorite vendorFavorite;

    @OneToMany(mappedBy = "vendor")
    private List<Product> products;

    @OneToMany(mappedBy = "vendor")
    private List<Coupon> coupons;

    public Vendor() {
        products = new ArrayList<>();
    }

}
