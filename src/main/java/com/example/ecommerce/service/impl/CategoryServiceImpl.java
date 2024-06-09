package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.domain.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final IImageService imageService;
    private final IFilesStorageService filesStorageService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, IImageService imageService, IFilesStorageService filesStorageService) {
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
        this.filesStorageService = filesStorageService;
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

    @Transactional
    public CategoryDto saveOrUpdate(CategoryDto categoryDto) {
        Category cate = null;
//        Image image = null;
//        if(categoryDto.getId() != null) {
//            Category category = categoryRepository.findById(categoryDto.getId()).get();
//            if(category.getThumbnail() != null) {
//                try {
//                    filesStorageService.deleteFile(category.getThumbnail().getName(),
//                            SystemUtils.FOLDER_CATEGORY_IMAGE);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                image = category.getThumbnail().toBuilder()
//                        .name(categoryDto.getFile().getOriginalFilename())
//                        .build();
//            }
//            category.setName(categoryDto.getName());
//            cate = category;
//        } else {
//            cate = (Category) Convert.CATE.toEntity(categoryDto);
//            image = imageService.uploadFile(categoryDto.getFile(),
//                    SystemUtils.FOLDER_CATEGORY_IMAGE,
//                    SystemUtils.SHORT_URL_CATEGORY);
//        }
//        cate.setThumbnail(image);
        CategoryDto dto =  (CategoryDto) Convert.CATE.toDto(categoryRepository.save(cate));
        return dto;
    }
}
