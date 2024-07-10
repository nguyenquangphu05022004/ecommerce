package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.EntityType;
import com.example.ecommerce.service.dto.CategoryDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.mapper.ImageMapper;
import com.example.ecommerce.service.request.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "categoryMapper")
public class CategoryMapper extends ImageMapper implements
        IMapper<Category, CategoryRequest, CategoryDto> {
    @Override
    public Category toEntity(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .slug(categoryRequest.getSlug())
                .name(categoryRequest.getName())
                .id(categoryRequest.getId())
                .build();
        return category;
    }

    @Override
    public CategoryDto toDto(Category category) {
        CategoryDto response = CategoryDto.builder()
                .slug(category.getSlug())
                .name(category.getName())
                .imageUrl(getImageUrl(EntityType.CATEGORY.name(), category.getImage()))
                .id(category.getId())
                .build();
        return response;
    }

    @Override
    public List<CategoryDto> toDtoList(List<Category> categories) {
        List<CategoryDto> responses = new ArrayList<>();
        if(categories != null) {
            categories.forEach(cate -> responses.add(toDto(cate)));
        }
        return responses;
    }
}
