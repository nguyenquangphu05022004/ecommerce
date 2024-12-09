package com.example.ecommerce.promotion.dal.repo.coupon;

import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String code);
    List<Coupon> findAllByCreatedBy(Long userId);
}
