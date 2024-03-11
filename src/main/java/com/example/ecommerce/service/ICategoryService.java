package com.example.ecommerce.service;

import com.example.ecommerce.dto.CategoryDto;

public interface ICategoryService {
    CategoryDto saveOrUpdate(CategoryDto categoryDto);
}
