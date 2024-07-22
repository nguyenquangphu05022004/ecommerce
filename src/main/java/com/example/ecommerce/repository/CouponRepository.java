package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByVendorIdAndAndCode(Long vendorId, String code);
}
