package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Image;
import com.example.ecommerce.domain.dto.CategoryDto;
import com.example.ecommerce.domain.dto.CategoryRequest;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.common.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("categoryService")
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final IImageService imageService;
    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> mapToDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }


    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("CategoryId", id + "")
                );
        return mapToDto(category);
    }

    @Transactional
    @Override
    public void save(CategoryRequest request) {
        Optional<Category> optional = categoryRepository
                                        .findById(request.getId());
        if(optional.isPresent()) {
            Category category = optional.get();
            if(!(request.getName().isEmpty() || request.getName().isBlank())) {
                category.setName(request.getName());
            }
            if(request.getFile() != null) {
                if(category.getImage() != null) {
                    imageService.deleteFile(
                            category.getImage().getName(),
                            category.getImage().getId()
                    );
                }
                Image image = imageService.uploadFile(request.getFile(), SystemUtils.TAG);
                category.setImage(image);
            }
        } else {
            Category category = Category.builder()
                    .name(request.getName())
                    .build();
            if(request.getFile() != null) {
                Image image = imageService.uploadFile(request.getFile(), SystemUtils.TAG);
                category.setImage(image);
            }
            category = categoryRepository.save(category);
        }
    }

    private CategoryDto mapToDto(Category category) {
        return mapper.map(category, CategoryDto.class)
                .toBuilder()
                .numberOfProduct(category.getProducts().size())
                .build();
    }
}
