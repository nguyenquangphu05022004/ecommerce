package com.example.ecommerce.domain.entities.order;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.auth.Vendor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Entity
@Table(name = "line_items")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Setter
public class LineItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
    @OneToMany(mappedBy = "lineItem")
    private Set<Item> items;
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
