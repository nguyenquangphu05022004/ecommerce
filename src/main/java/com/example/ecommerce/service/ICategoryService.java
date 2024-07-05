package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.CategoryDto;
import com.example.ecommerce.domain.dto.CategoryRequest;

import java.util.List;

public interface ICategoryService {
    void save(CategoryRequest request);
    CategoryDto findById(Long id);
    void delete(Long id);
    List<CategoryDto> findAll();
}
