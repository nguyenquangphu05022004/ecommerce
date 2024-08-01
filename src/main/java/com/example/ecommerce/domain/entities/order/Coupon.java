package com.example.ecommerce.domain.entities.order;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.auth.Vendor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class Coupon extends BaseEntity {
    private int moneyDecrease;
    private LocalDateTime start;
    private LocalDateTime end;
    private String code;
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    @Transient
    public boolean isExpired() {
        if(LocalDateTime.now().isAfter(start) && LocalDateTime.now().isBefore(end)) {
            return false;
        }
        return true;
    }

    public Coupon(Long id) {
        super(id);
    }
}
