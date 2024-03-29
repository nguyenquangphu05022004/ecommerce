package com.example.ecommerce.service;

import com.example.ecommerce.dto.CategoryDto;

public interface ICategoryService extends IGenericService<CategoryDto>{
    CategoryDto saveOrUpdate(CategoryDto categoryDto);
}
