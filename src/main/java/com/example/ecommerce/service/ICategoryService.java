package com.example.ecommerce.service;




import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.model.binding.CategoryRequest;

import java.util.List;

public interface ICategoryService {
    void save(CategoryRequest request);

    List<Category> getAllCategory();
}
