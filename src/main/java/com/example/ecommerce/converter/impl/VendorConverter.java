package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.VendorDto;
import com.example.ecommerce.domain.Vendor;
import org.modelmapper.ModelMapper;

public class VendorConverter implements IGenericConverter<Vendor, VendorDto> {
    private  final ModelMapper mapper;
    public VendorConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Vendor toEntity(VendorDto vendorDto) {

        return mapper.map(vendorDto, Vendor.class);
    }

    @Override
    public VendorDto toDto(Vendor vendor) {
        return mapper.map(vendor, VendorDto.class);
    }

    @Override
    public Vendor toEntity(Vendor vendor, VendorDto vendorDto) {
        return null;
    }
}
