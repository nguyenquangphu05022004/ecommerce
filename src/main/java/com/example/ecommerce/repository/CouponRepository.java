package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query("select c from Coupon c inner " +
            "join Product p on c.vendor.id = p.vendor.id where p.id = :productId and c.code = :code and c.expired=false")
    Optional<Coupon> findByCodeAndProductIdAndExpiredIsFalse(@Param("code") String code,@Param("productId") Long productId);
    @Query("select c from Coupon c inner " +
            "join Product p on c.vendor.id = p.vendor.id where p.id = :productId and c.id= :id and c.expired = false")
    Optional<Coupon> findByIdAndProductIdAndExpiredIsFalse(@Param("id") Long id,@Param("productId") Long productId);

    List<Coupon> findAllByVendorIdOrderByStartDesc(Long vendorId);
    List<Coupon> findAllByOrderByStartDesc();
}
