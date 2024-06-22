package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.chat.VendorResponseInbox;
import com.example.ecommerce.domain.dto.VendorDto;

import java.util.List;

public interface IVendorService extends IGenericService<VendorDto> {
    VendorDto saveOrUpdate(VendorDto vendorDto);

    List<VendorResponseInbox> findAllByName(String vendorName);
}
