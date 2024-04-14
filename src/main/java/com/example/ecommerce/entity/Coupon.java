package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@Getter
@Data
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
    private Boolean expired;
    private Boolean started;


}
