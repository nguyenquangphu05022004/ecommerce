package com.example.ecommerce.converter;

import com.example.ecommerce.domain.dto.product.CategoryDto;
import com.example.ecommerce.domain.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final ModelMapper mapper;

    public Category toEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }
    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
        return categoryDto;
    }
    public Category toEntity(Category category, CategoryDto categoryDto) {
        return null;
    }
}
