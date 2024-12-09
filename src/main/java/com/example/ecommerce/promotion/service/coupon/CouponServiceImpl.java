package com.example.ecommerce.promotion.service.coupon;

import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.promotion.controller.coupon.CouponCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;
import com.example.ecommerce.promotion.dal.repo.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.promotion.dal.enums.ErrorConstant.COUPON_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    @Override
    public Coupon createCoupon(CouponCreateReqVO reqVO) {
        Coupon coupon = Coupon.builder().end(reqVO.getEnd()).begin(reqVO.getBegin())
                .couponScope(reqVO.getCouponScope()).countNumber(0)
                .description(reqVO.getDescription()).limitMaxPrice(reqVO.getLimitMaxPrice())
                .limitMinPrice(reqVO.getLimitMinPrice()).productScope(reqVO.getProductScope())
                .code(RandomUtils.randomString()).build();
        this.couponRepository.save(coupon);
        return coupon;
    }

    @Override
    public Coupon getCouponByCode(String code) {
        return this.couponRepository.findByCode(code)
                .orElseThrow(() -> exception(COUPON_NOT_FOUND));
    }

    @Override
    public List<Coupon> getListCoupon(Long userId) {
        return this.couponRepository.findAllByCreatedBy(userId);
    }
}
