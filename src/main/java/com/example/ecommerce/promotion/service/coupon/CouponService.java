package com.example.ecommerce.promotion.service.coupon;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.promotion.controller.coupon.CouponCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.trade.enums.ErrorConstants.COUPON_EXPIRED;
import static com.example.ecommerce.trade.enums.ErrorConstants.COUPON_UNUSED;

public interface CouponService {
    Coupon createCoupon(CouponCreateReqVO reqVO);
    Coupon getCouponByCode(String code);
    default Coupon getValidCoupon(String code) {
        Coupon coupon = getCouponByCode(code);
        if(!DateTimeUtils.isBetween(coupon.getBeginDate(), coupon.getEndDate())) {
            if(DateTimeUtils.isExpired(coupon.getEndDate())) {
                throw exception(COUPON_EXPIRED);
            } else {
                throw exception(COUPON_UNUSED);
            }
        }
        return coupon;
    }
    /**
     * Lay phieu giam gia boi nguoi tao no
     * @param userId: seller
     * @return
     */
    List<Coupon> getListCoupon(Long userId);



}
