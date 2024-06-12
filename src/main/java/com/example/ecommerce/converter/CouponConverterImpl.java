package com.example.ecommerce.converter;

import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.domain.dto.product.CouponDto;
import com.example.ecommerce.domain.Coupon;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponConverterImpl {
    private final ModelMapper mapper;
    public Coupon toEntity(CouponDto couponDto) {
        Coupon coupon = mapper.map(couponDto, Coupon.class)
                .toBuilder()
                .vendor(Vendor.builder().id(couponDto.getVendorId())
                        .build())
                .build();
        return coupon;
    }

    public CouponDto toDto(Coupon e) {
        return null;
    }

    public Coupon toEntity(Coupon coupon, CouponDto couponDto) {
        return null;
    }
}
