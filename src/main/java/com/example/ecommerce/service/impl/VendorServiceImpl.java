package com.example.ecommerce.service.impl;

import com.example.ecommerce.constant.Convert;
import com.example.ecommerce.dto.VendorDto;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.entity.Vendor;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IGenericService;
import com.example.ecommerce.service.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl  implements IGenericService<VendorDto>, IVendorService {
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, UserRepository userRepository) {
        this.vendorRepository = vendorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<VendorDto> records() {
        return GenericService.records(vendorRepository, Convert.VEND);
    }

    @Override
    public void delete(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return vendorRepository.count();
    }

    @Override
    public VendorDto findById(Long id) {
        return (VendorDto) Convert.VEND.toDto(
                GenericService.findById(vendorRepository, id)
        );
    }

    @Override
    public VendorDto saveOrUpdate(VendorDto vendorDto) {
        User user = userRepository.findById(vendorDto.getUser().getId()).get();
        user.setRole(vendorDto.getUser().getRole());
        Vendor vendor = new Vendor();
        vendor.setUser(user);
        vendor.setShopName(vendorDto.getShopName());
        return (VendorDto) Convert.VEND.toDto(vendorRepository.save(vendor));
    }
}
