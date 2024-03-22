package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryDto> records() {
        System.out.println(SecurityUtils.username());
        return GenericService.records(categoryRepository, Convert.CATE);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return categoryRepository.count();
    }

    @Override
    public CategoryDto findById(Long id) {
        return (CategoryDto) Convert.CATE.toDto(
                GenericService.findById(categoryRepository, id)
        );
    }

    public CategoryDto saveOrUpdate(CategoryDto categoryDto) {
        Category cate = null;
        if(categoryDto.getId() != null) {

        } else {
            cate = (Category) Convert.CATE.toEntity(categoryDto);
        }
        return (CategoryDto) Convert.CATE.toDto(categoryRepository.save(cate));
    }
}
