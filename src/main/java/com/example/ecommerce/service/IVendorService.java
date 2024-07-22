package com.example.ecommerce.service;

import com.example.ecommerce.service.request.CouponRequest;
import com.example.ecommerce.service.request.VendorRequest;
import com.example.ecommerce.service.response.APIResponse;
import com.example.ecommerce.service.response.CouponResponse;

public interface IVendorService{
    void saveOrUpdate(VendorRequest request);
    void userFollow(Long vendorId);
    void createCoupon(CouponRequest request);
    APIResponse<CouponResponse> checkCouponExpire(Long vendorId, String couponCode);
}
