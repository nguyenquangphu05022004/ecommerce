package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.service.dto.VendorDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.VendorRequest;
import org.springframework.stereotype.Service;

@Service("vendorMapper")
public class VendorMapper implements IMapper<Vendor, VendorRequest, VendorDto> {
    @Override
    public VendorDto toDto(Vendor vendor) {
        VendorDto vendorDto = VendorDto.builder()
                .numberOfProduct(ValidationUtils.fieldCheckNullOrEmpty(vendor.getProducts()))
                .perMoneyDelivery(vendor.getPerMoneyDelivery())
                .shopName(vendor.getShopName())
                .id(vendor.getId())
                .dateParticipate(SystemUtils.getFormatDate(vendor.getCreatedDate(), "dd/MM/yyyy"))
                .build();
        return vendorDto;
    }

    @Override
    public Vendor toEntity(VendorRequest vendorRequest) {
        Vendor vendor = Vendor.builder()
                .shopName(vendorRequest.getShopName())
                .perMoneyDelivery(vendorRequest.getPerMoneyDelivery())
                .build();
        return vendor;
    }
}
