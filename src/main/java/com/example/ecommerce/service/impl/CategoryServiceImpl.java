package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.CategoryImage;
import com.example.ecommerce.domain.EntityType;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.dto.CategoryDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.CategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    @Qualifier(value = "categoryMapper")
    private final IMapper<Category, CategoryRequest, CategoryDto> mapper;
    private final IFilesStorageService filesStorageService;
    @Override
    public List<CategoryDto> findAll() {
        var categories =  categoryRepository.findAll();
        return mapper.toDtoList(categories);
    }


    @Override
    public CategoryDto findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {
            return mapper.toDto(optionalCategory.get());
        }
        throw new GeneralException(String.format("Category id %s not found", id));
    }

    @Transactional
    @Override
    public void save(CategoryRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getName(), "Category Name");
        ValidationUtils.fieldCheckNullOrEmpty(request.getSlug(), "Category Slug");
        Category category = mapper.toEntity(request);
        if(category.getId() != null) {
            Category oldCate = categoryRepository
                    .findById(category.getId())
                    .orElseThrow(() -> new GeneralException("Category not found id " + request.getId()));
            filesStorageService.deleteImage(oldCate.getImage());
            category = mapper.toEntity(request, oldCate);
        }
        categoryRepository.save(category);
        filesStorageService.saveFile(request.getFile(), category.getId(), EntityType.CATEGORY);
    }
}
