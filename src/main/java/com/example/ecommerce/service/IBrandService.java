package com.example.ecommerce.service;

import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.binding.BrandRequest;

import java.util.List;

public interface IBrandService {
    void createBrand(BrandRequest proBrand);
    List<ProductBrand> getAllBrand();
}
