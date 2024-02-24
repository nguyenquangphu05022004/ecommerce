package com.example.ecommerce.service.impl;

import com.example.ecommerce.constant.Convert;
import com.example.ecommerce.dto.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    public CategoryDto saveOrUpdate(CategoryDto categoryDto) {
        return (CategoryDto) Convert.CATE.toEntity(categoryDto);
    }
}
