package com.example.ecommerce.service;

import com.example.ecommerce.domain.model.binding.BrandRequest;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import org.springframework.data.jpa.repository.Modifying;

public interface IBrandService {
    APIResponse<?> createBrand(BrandRequest proBrand);
    APIListResponse<?> getAllBrand(int page, int limit);
}
