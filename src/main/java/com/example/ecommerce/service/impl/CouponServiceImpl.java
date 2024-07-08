package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Coupon;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.domain.dto.CouponDto;
import com.example.ecommerce.repository.CouponRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.ICouponService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements ICouponService {
    private final VendorRepository vendorRepository;
    private final CouponRepository couponRepository;
    private final ModelMapper mapper;

    @Override
    public void createCoupon(CouponDto couponDto) {
        Vendor vendor = vendorRepository.findByUserUsername(SecurityUtils.username());
        Coupon coupon = mapper.map(couponDto, Coupon.class)
                .toBuilder()
                .vendor(vendor)
                .build();
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
        return null;
    }


    private List<CouponDto> toDto(List<Coupon> coupons) {
        return coupons.stream()
                .map(e -> mapper.map(e, CouponDto.class)
                        .toBuilder()
                        .isExpired(e.couponIsExpired())
                        .build())
                .collect(Collectors.toList());
    }
}
