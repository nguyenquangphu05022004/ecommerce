package com.example.ecommerce.service;


import com.example.ecommerce.service.dto.CategoryDto;
import com.example.ecommerce.service.request.CategoryRequest;

import java.util.List;

public interface ICategoryService {
    void save(CategoryRequest request);
    CategoryDto findById(Long id);
    void delete(Long id);
    List<CategoryDto> findAll();
}
