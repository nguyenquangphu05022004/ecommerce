package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.converter.CouponConverterImpl;
import com.example.ecommerce.domain.Coupon;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.domain.dto.product.CouponDto;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CouponRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.ICouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements ICouponService {
    private final VendorRepository vendorRepository;
    private final CouponRepository couponRepository;
    private final CouponConverterImpl couponConverter;

    @Override
    @Transactional
    public void createCoupon(CouponDto couponDto) {
        Vendor vendor = vendorRepository.findByUserUsername(SecurityUtils.username());
        Coupon coupon = couponConverter.toEntity(couponDto).toBuilder().vendor(vendor).build();
        couponRepository.save(coupon);
    }

    @Override
    public List<CouponDto> list() {
        List<Coupon> coupons = couponRepository.findAllByOrderByStartDesc();
        return toDto(coupons);
    }
    @Override
    public List<CouponDto> listByVendor() {
        Vendor vendor = vendorRepository.findByUserUsername(SecurityUtils.username());
        return toDto(vendor.getCoupons());
    }

    @Override
    public List<CouponDto> listByVendor(Long vendorId) {
        List<Coupon> list = couponRepository.findAllByVendorIdOrderByStartDesc(vendorId);
        return toDto(list);
    }

    @Override
    public CouponDto findByCodeAndProductId(String code, Long productId) {
        Optional<Coupon> opCoupon = couponRepository.findByCodeAndProductIdAndExpiredIsFalse(code, productId);
        if(opCoupon.isEmpty() || opCoupon.get().couponIsExpired()) {
            throw new NotFoundException("Code", code);
        } else {
            Coupon coupon = opCoupon.get();
            Vendor vendor = coupon.getVendor()
                    .toBuilder().products(null).coupons(null).user(null)
                    .build();
            coupon.setVendor(vendor);
            return couponConverter.toDto(coupon);
        }
    }


    private List<CouponDto> toDto(List<Coupon> coupons) {
        return coupons.stream().map(e -> couponConverter.toDto(e)).collect(Collectors.toList());
    }
}
