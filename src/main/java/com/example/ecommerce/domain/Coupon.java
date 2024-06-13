package com.example.ecommerce.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Coupon extends Base {
    private String code;
    private String content;
    private Integer percent;
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    private LocalDateTime start;
    private LocalDateTime end;

    public boolean couponIsExpired() {
        if(start != null && end != null) {
            if(LocalDateTime.now().isAfter(start) || LocalDateTime.now().isBefore(end)) {
                return false;
            }
            return true;
        }
        return false;
    }


}
