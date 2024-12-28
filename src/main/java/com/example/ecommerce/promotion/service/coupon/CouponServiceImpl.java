package com.example.ecommerce.promotion.service.coupon;

import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.promotion.controller.admin.coupon.vo.CouponCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;
import com.example.ecommerce.promotion.dal.repo.coupon.CouponRepository;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.promotion.dal.enums.ErrorConstant.COUPON_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final UserMemberService userMemberService;
    @Override
    public Coupon createCoupon(CouponCreateReqVO reqVO) {
        Coupon coupon = Coupon.builder().endDate(reqVO.getEnd()).beginDate(reqVO.getBegin())
                .couponScope(reqVO.getCouponScope()).countNumber(0)
                .description(reqVO.getDescription()).limitMaxPrice(reqVO.getLimitMaxPrice())
                .limitMinPrice(reqVO.getLimitMinPrice()).productScope(reqVO.getProductScope())
                .owner(userMemberService.getUserMemberById(reqVO.getOwnerId()))
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
    public Coupon getCouponById(Long id) {
        return this.couponRepository.findById(id)
                .orElseThrow(() -> exception(COUPON_NOT_FOUND));
    }

    @Override
    public void revokeCoupon(Long id) {
        Coupon coupon = getCouponById(id);
        coupon.setRevokeCoupon(true);
        this.couponRepository.save(coupon);
    }

    @Override
    public PageResult<Coupon> getPageCoupon(Long userId, PageParam req) {
        return new PageResult<>(couponRepository.findAllByOwnerId(userId, req.buildPageRequest()));
    }

}
