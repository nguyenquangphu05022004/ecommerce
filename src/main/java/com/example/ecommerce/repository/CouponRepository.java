package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query("select c from Coupon c inner join Vendor v on c.vendor.id = v.id inner join " +
            "Product p on p.vendor.id = v.id where p.id = :productId and c.code = :code")
    Optional<Coupon> findByCodeAndProductId(@Param("code") String code,
                                            @Param("productId") Long productId);
    List<Coupon> findAllByVendorIdOrderByStartDesc(Long vendorId);
    List<Coupon> findAllByOrderByStartDesc();
}
