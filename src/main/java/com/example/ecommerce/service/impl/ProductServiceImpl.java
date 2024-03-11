package com.example.ecommerce.service.impl;

import com.example.ecommerce.constant.Convert;
import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.IGenericService;
import com.example.ecommerce.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IGenericService<ProductDto>, IProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> records() {
        return GenericService.records(productRepository, Convert.PRO);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return productRepository.count();
    }

    @Override
    public ProductDto findById(Long id) {
        return (ProductDto) Convert.PRO.toDto(
                GenericService.findById(productRepository, id)
        );
    }

    @Override
    public ProductDto saveOrUpdate(ProductDto productDto, Long categoryId) {
        Category category = new Category(); category.setId(categoryId);
        Product product = new Product();
        if(productDto.getId() != null) {

        } else {
            product = (Product) Convert.PRO.toEntity(productDto);
        }
        product.setCategory(category);
        return (ProductDto) Convert.PRO.toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> searchProductsByName(String name) {
        List<Product> products = productRepository.findAllByName(name);
        return products.stream()
                .map(entity -> (ProductDto)Convert.PRO.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream()
                .map((entity) -> (ProductDto)Convert.PRO.toDto(entity))
                .collect(Collectors.toList());
    }
}
