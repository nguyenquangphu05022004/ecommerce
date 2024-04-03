package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Image;
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
        return Category.builder()
                .thumbnail(categoryDto.getImage())
                .name(categoryDto.getName())
                .build();
    }

    @Override
    public CategoryDto toDto(Category category) {
        Image image = category.getThumbnail();
        if(image != null) {
            image = image.toBuilder().category(null).build();
        }
        CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
        categoryDto.setImage(image);
        return categoryDto;
    }

    @Override
    public Category toEntity(Category category, CategoryDto categoryDto) {
        return null;
    }
}
