package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.model.binding.CategoryRequest;
import com.example.ecommerce.domain.model.modelviews.messages.MessageModelView;
import com.example.ecommerce.domain.model.modelviews.product.CategoryModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.NotFoundException;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.service.IFilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.ecommerce.domain.entities.EntityType.Type.CATEGORY;
import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final IFilesStorageService filesStorageService;

    @Transactional
    @Override
    public APIResponse<?> save(CategoryRequest request) {
        Category category = new Category();
        String message = "created";
        if (request.getId() != null) {
            category = categoryRepository.findById(request.getId())
                    .orElseThrow(() -> new NotFoundException("not found category"))
                    .toBuilder()
                    .build();
            message = "updated";
        }
        category = category.toBuilder()
                .slug(request.getSlug())
                .name(request.getName())
                .build();

        if(request.getParentId() != null) {
            category.setParent(Category.builder().id(request.getParentId()).build());
        }
        if(request.getFile() != null) {
            category.getImages().add(filesStorageService.saveFile(
                    request.getFile(),
                    new EntityType(CATEGORY, category.getId())
            ));
        }
        categoryRepository.save(category);
        return  apiResponse(message +" category", new CategoryModelView(category));
    }

    @Override
    public APIListResponse<?> getAllCategoryParent(int page, int limit) {
        Page<Category> pages = categoryRepository
                .findAllByParentIsNull(PageRequest.of(page - 1, limit));
        return new APIListResponse<>(
                200, page, limit, pages.getTotalPages(),
                pages.stream().map(c -> new CategoryModelView(c)).toList()
        );
    }

    /**
     * Method for test
     * @param name
     */
    @Override
    @Transactional
    public void deleteByName(String name) {
        Category category = categoryRepository.findByName(name).orElse(null);
        if(category != null) {
            filesStorageService.deleteImage(category.getImages());
            categoryRepository.delete(category);
        }
    }
}
