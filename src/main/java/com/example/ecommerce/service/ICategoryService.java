package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.product.CategoryDto;

public interface ICategoryService extends IGenericService<CategoryDto>{
    CategoryDto saveOrUpdate(CategoryDto categoryDto);
}
