package com.example.ecommerce.promotion.service.coupon;

import com.example.ecommerce.promotion.controller.coupon.CouponCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;

import java.util.List;

public interface CouponService {
    Coupon createCoupon(CouponCreateReqVO reqVO);
    Coupon getCouponByCode(String code);

    /**
     * Lay phieu giam gia boi nguoi tao no
     * @param userId: seller
     * @return
     */
    List<Coupon> getListCoupon(Long userId);
}
