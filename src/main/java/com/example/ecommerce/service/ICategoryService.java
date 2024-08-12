package com.example.ecommerce.service;




import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.model.binding.CategoryRequest;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;

import java.util.List;

public interface ICategoryService {
    APIResponse<?> save(CategoryRequest request);

    APIListResponse<?> getAllCategoryParent(int page, int limit);
}
