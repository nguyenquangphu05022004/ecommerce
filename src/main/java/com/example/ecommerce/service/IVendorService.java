package com.example.ecommerce.service;

import com.example.ecommerce.domain.response.vendor.VendorResponseInbox;
import com.example.ecommerce.dto.CouponDto;
import com.example.ecommerce.dto.VendorDto;

import java.util.List;

public interface IVendorService {
    VendorDto saveOrUpdate(VendorDto vendorDto);

    List<VendorResponseInbox> findAllByName(String vendorName);
}
