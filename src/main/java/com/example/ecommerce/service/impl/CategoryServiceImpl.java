package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.model.binding.CategoryRequest;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.service.IFilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final IFilesStorageService filesStorageService;

    @Transactional
    @Override
    public void save(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .build();
        boolean isUpdate = false;
        if (category.getId() != null) {
            Category oldCate = categoryRepository
                    .findById(category.getId())
                    .orElseThrow(() -> new GeneralException("Category not found id " + request.getId()));
            if (request.getFile() != null) {
                filesStorageService.deleteImage(oldCate.getImage());
            }
            category = category.toBuilder()
                    .name(request.getName())
                    .slug(request.getSlug())
                    .build();
            isUpdate = true;
        }
        categoryRepository.save(category);
        if (!isUpdate && request.getFile() == null) {
            throw new GeneralException("file can't null");
        }
        filesStorageService.saveFile(request.getFile(), category.getId(), FileEntityType.CATEGORY);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAllByParentIsNull();
    }
}
