package com.example.ecommerce.converter;
import com.example.ecommerce.domain.dto.user.VendorDto;
import com.example.ecommerce.domain.Vendor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class VendorConverter{
    private  final ModelMapper mapper;

    public Vendor toEntity(VendorDto vendorDto) {

        return mapper.map(vendorDto, Vendor.class);
    }
    public VendorDto toDto(Vendor vendor) {
        return mapper.map(vendor, VendorDto.class);
    }
}
