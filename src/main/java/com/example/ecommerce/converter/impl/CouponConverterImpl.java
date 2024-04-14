package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.CouponDto;
import com.example.ecommerce.dto.VendorDto;
import com.example.ecommerce.entity.Coupon;
import com.example.ecommerce.utils.Convert;
import org.springframework.stereotype.Component;

@Component
public class CouponConverterImpl implements IGenericConverter<Coupon, CouponDto> {
    @Override
    public Coupon toEntity(CouponDto couponDto) {
        return Coupon.builder()
                .end(couponDto.getEnd()).start(couponDto.getStart())
                .percent(couponDto.getPercent()).content(couponDto.getContent())
                .started(false).expired(false).code(couponDto.getCode()).build();
    }

    @Override
    public CouponDto toDto(Coupon e) {
        if(e == null) return null;
        return CouponDto.builder()
                .id(e.getId())
                .start(e.getStart()).end(e.getEnd()).content(e.getContent())
                .percent(e.getPercent()).code(e.getCode())
                .vendor((VendorDto) Convert.VEND.toDto(e.getVendor()))
                .expired(e.getExpired()).started(e.getStarted()).build();
    }

    @Override
    public Coupon toEntity(Coupon coupon, CouponDto couponDto) {
        return null;
    }
}
