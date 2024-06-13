package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.product.CategoryDto;
import com.example.ecommerce.domain.dto.product.CategoryRequest;

public interface ICategoryService extends IGenericService{
    CategoryDto saveOrUpdate(CategoryRequest request);
}
