package com.example.ecommerce.promotion.dal.repo.coupon;

import com.example.ecommerce.promotion.dal.dataobject.coupon.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
