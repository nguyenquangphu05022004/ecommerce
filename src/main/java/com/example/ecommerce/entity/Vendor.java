package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendors")
@Data
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)

public class Vendor extends Base{
    /*
    vendor:
        id, nameShop, listProduct, perMoneyDelivery
     */
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

}
