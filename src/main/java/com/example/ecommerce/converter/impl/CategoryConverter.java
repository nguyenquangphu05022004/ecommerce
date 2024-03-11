package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.entity.Category;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class CategoryConverter implements IGenericConverter<Category, CategoryDto> {
    private final ModelMapper mapper;

    public CategoryConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        Category c = mapper.map(categoryDto, Category.class);
        return c;
    }

    @Override
    public CategoryDto toDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public Category toEntity(Category category, CategoryDto categoryDto) {
        return null;
    }
}
