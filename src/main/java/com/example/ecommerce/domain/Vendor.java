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
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Vendor extends BaseEntity {
    @Column(columnDefinition = "nvarchar(100)",nullable = false)
    private String shopName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "vendor")
    private List<Product> products = new ArrayList<>();
    private Integer perMoneyDelivery;
    @OneToMany(mappedBy = "vendor")
    private List<Coupon> coupons = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_favorite_id")
    private VendorFavorite vendorFavorite;
}
