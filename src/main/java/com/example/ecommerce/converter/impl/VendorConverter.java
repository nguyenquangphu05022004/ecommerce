package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.VendorDto;
import com.example.ecommerce.entity.Vendor;

public class VendorConverter implements IGenericConverter<Vendor, VendorDto> {
    @Override
    public Vendor toEntity(VendorDto vendorDto) {
        return null;
    }

    @Override
    public VendorDto toDto(Vendor vendor) {
        return null;
    }

    @Override
    public Vendor toEntity(Vendor vendor, VendorDto vendorDto) {
        return null;
    }
}
