package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.file.EntityType;
import com.example.ecommerce.service.dto.CategoryDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.mapper.ImageMapper;
import com.example.ecommerce.service.request.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service(value = "categoryMapper")
public class CategoryMapper implements ImageMapper ,
        IMapper<Category, CategoryRequest, CategoryDto> {
    @Override
    public Category toEntity(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .slug(categoryRequest.getSlug())
                .name(categoryRequest.getName())
                .id(categoryRequest.getId())
                .parent(categoryRequest.getParentId() != null ? Category.builder().id(categoryRequest.getParentId()).build() : null)
                .build();
        return category;
    }

    @Override
    public CategoryDto toDto(Category category) {
        if(category == null) return null;
        CategoryDto response = CategoryDto.builder()
                .slug(category.getSlug())
                .name(category.getName())
                .imageUrl(getImageUrl(EntityType.CATEGORY.name(), category.getImage()))
                .children(toDtoList(category.getChildren()))
                .id(category.getId())
                .build();
        return response;
    }

    @Override
    public List<CategoryDto> toDtoList(Collection<? extends Category> categories) {
        List<CategoryDto> responses = new ArrayList<>();
        if(categories != null) {
            categories.forEach(cate -> responses.add(toDto(cate)));
        }
        return responses;
    }
}
