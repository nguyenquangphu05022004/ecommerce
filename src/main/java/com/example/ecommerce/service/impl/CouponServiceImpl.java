package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.converter.impl.CouponConverterImpl;
import com.example.ecommerce.dao.impl.CouponEventDao;
import com.example.ecommerce.dto.CouponDto;
import com.example.ecommerce.dto.VendorDto;
import com.example.ecommerce.entity.Coupon;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Vendor;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.exception.NotValidException;
import com.example.ecommerce.repository.CouponRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.ICouponService;
import com.example.ecommerce.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements ICouponService {
    private final VendorRepository vendorRepository;
    private final CouponRepository couponRepository;
    private final CouponEventDao couponEventDao;
    private final CouponConverterImpl couponConverter;
    @Autowired
    public CouponServiceImpl(VendorRepository vendorRepository,
                             CouponRepository couponRepository,
                             CouponEventDao couponEventDao,
                             CouponConverterImpl couponConverter) {
        this.vendorRepository = vendorRepository;
        this.couponRepository = couponRepository;
        this.couponEventDao = couponEventDao;
        this.couponConverter = couponConverter;
    }

    @Override
    @Transactional
    public void createCoupon(CouponDto couponDto) {
        Vendor vendor = vendorRepository.findByUserUsername(SecurityUtils.username());
        Coupon coupon = couponConverter.toEntity(couponDto).toBuilder().vendor(vendor).build();
        couponEventDao.createEvent(couponRepository.save(coupon));
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
        if(opCoupon.isEmpty()) {
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
