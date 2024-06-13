package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.chat.VendorResponseInbox;
import com.example.ecommerce.domain.dto.user.VendorDto;

import java.util.List;

public interface IVendorService {
    VendorDto saveOrUpdate(VendorDto vendorDto);

    List<VendorResponseInbox> findAllByName(String vendorName);
}
