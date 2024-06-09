package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.domain.Category;
import org.modelmapper.ModelMapper;

public class CategoryConverter implements IGenericConverter<Category, CategoryDto> {
    private final ModelMapper mapper;

    public CategoryConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }

    @Override
    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    @Override
    public Category toEntity(Category category, CategoryDto categoryDto) {
        return null;
    }
}
