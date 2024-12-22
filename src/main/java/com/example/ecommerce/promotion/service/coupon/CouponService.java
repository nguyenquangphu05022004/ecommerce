package com.example.ecommerce.promotion.service.coupon;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.promotion.controller.coupon.CouponCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.trade.enums.ErrorConstants.*;

public interface CouponService {
    Coupon createCoupon(CouponCreateReqVO reqVO);
    Coupon getCouponByCode(String code);
    Coupon getCouponById(Long id);
    default Coupon getValidCoupon(String code) {
        Coupon coupon = getCouponByCode(code);
        if(coupon.getRevokeCoupon()) throw exception(COUPON_IS_REVOKED);
        if(!DateTimeUtils.isBetween(coupon.getBeginDate(), coupon.getEndDate())) {
            if(DateTimeUtils.isExpired(coupon.getEndDate())) {
                throw exception(COUPON_EXPIRED);
            } else {
                throw exception(COUPON_UNUSED);
            }
        }
        return coupon;
    }

    void revokeCoupon(Long id);

    /**
     * Lay phieu giam gia boi nguoi tao no
     * @param userId: seller
     * @return
     */
    List<Coupon> getListCoupon(Long userId);

}
