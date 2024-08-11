package com.example.ecommerce.service;

import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.binding.BrandRequest;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;

import java.util.List;

public interface IBrandService {
    APIResponse<?> createBrand(BrandRequest proBrand);
    APIListResponse<?> getAllBrand(int page, int limit);
}
