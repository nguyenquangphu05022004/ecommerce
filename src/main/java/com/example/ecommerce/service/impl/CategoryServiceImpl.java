package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.model.binding.CategoryRequest;
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
        if (request.getId() != null) {
            Category oldCate = categoryRepository.findById(request.getId())
                    .orElseThrow(() -> new NotFoundException("not found category"))
                    .toBuilder()
                    .name(request.getName())
                    .slug(request.getSlug())
                    .build();

            if(request.getFile() != null) {
                oldCate.getImages().add(filesStorageService.saveFile(
                        request.getFile(),
                        new EntityType(CATEGORY, oldCate.getId())
                ));
            }
            categoryRepository.save(oldCate);
        } else {
            Category category = Category.builder()
                    .name(request.getName())
                    .slug(request.getSlug())
                    .build();
            categoryRepository.save(category);

            if(request.getFile() != null) {
                category.setImages(List.of(filesStorageService.saveFile(
                        request.getFile(),
                        new EntityType(CATEGORY, category.getId())
                )));
            }
            categoryRepository.save(category);
        }
        return apiResponse("created category", null);
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
}
