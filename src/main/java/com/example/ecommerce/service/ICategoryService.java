package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.CategoryDto;
import com.example.ecommerce.domain.dto.CategoryRequest;

public interface ICategoryService extends IGenericService<CategoryDto>{
    CategoryDto saveOrUpdate(CategoryRequest request);
}
