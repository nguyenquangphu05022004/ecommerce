package com.example.ecommerce.service;

import com.example.ecommerce.service.request.VendorRequest;

public interface IVendorService{
    void saveOrUpdate(VendorRequest request);
    void userFollow(Long vendorId);
}
