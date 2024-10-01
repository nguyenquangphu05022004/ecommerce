package com.example.ecommerce.service;

import com.example.ecommerce.domain.model.binding.CouponRequest;
import com.example.ecommerce.domain.model.binding.VendorRequest;
import com.example.ecommerce.domain.response.APIResponse;

public interface IVendorService{
    APIResponse<?> createCoupon(CouponRequest request);
    APIResponse<?> checkCouponExpire(Long vendorId, String couponCode);
    APIResponse<?> userFollow(Long vendorId);
    APIResponse<?> cancelFollowVendor(Long userId, Long vendorId);

    void delete(Long entityId);
}
