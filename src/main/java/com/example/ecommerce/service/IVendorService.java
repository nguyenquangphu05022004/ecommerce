package com.example.ecommerce.service;

import com.example.ecommerce.dto.CouponDto;
import com.example.ecommerce.dto.VendorDto;

public interface IVendorService {
    VendorDto saveOrUpdate(VendorDto vendorDto);
}
