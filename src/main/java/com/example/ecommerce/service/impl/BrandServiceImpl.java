package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.binding.BrandRequest;
import com.example.ecommerce.service.IBrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements IBrandService {
    @Override
    public void createBrand(BrandRequest proBrand) {

    }

    @Override
    public List<ProductBrand> getAllBrand() {
        return null;
    }
}
