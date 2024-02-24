package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.entity.Category;

public class CategoryConverter implements IGenericConverter<Category, CategoryDto> {
    @Override
    public Category toEntity(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto toDto(Category category) {
        return null;
    }

    @Override
    public Category toEntity(Category category, CategoryDto categoryDto) {
        return null;
    }
}
