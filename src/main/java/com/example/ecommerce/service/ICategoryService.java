package com.example.ecommerce.service;


import com.example.ecommerce.domain.model.binding.CategoryRequest;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;

public interface ICategoryService {
    APIResponse<?> save(CategoryRequest request);

    APIListResponse<?> getAllCategoryParent(int page, int limit);

    void deleteByName(String name);
}
