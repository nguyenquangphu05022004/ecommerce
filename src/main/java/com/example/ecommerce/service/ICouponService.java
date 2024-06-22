package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.CouponDto;

import java.util.List;

public interface ICouponService {
    void createCoupon(CouponDto couponDto);
    List<CouponDto> list();
    List<CouponDto> listByVendor();
    List<CouponDto> listByVendor(Long vendorId);

    CouponDto findByCodeAndProductId(String code, Long productId);

}
