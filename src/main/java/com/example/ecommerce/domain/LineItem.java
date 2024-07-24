package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Entity
@Table(name = "line_items")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class LineItem extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Item> items;
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    @Entity
    @Table(name = "items")
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @SuperBuilder(toBuilder = true)
    static class Item extends BaseEntity{
        @ManyToOne
        @JoinColumn(name = "stock_id")
        private Stock stock;
        @ManyToOne
        @JoinColumn(name = "stock_classificationId")
        private StockClassification stockClassification;
        @Column(nullable = false)
        private Integer quantity;
    }
}
