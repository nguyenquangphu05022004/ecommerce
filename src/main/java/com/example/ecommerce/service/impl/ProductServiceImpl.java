package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Vendor;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("productService")
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.vendorRepository = vendorRepository;
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
    @Transactional
    public ProductDto saveOrUpdate(ProductDto productDto) {
        Vendor vendor = vendorRepository.findByUserUsername(SecurityUtils.username());
        Product product = new Product();
        if (productDto.getId() != null) {

        } else {
            product = (Product) Convert.PRO.toEntity(productDto);
        }
        SystemUtils.FILES_STORAGE_SERVICE
                .saveFile(productDto.getMultipartFile(),
                        SystemUtils.FOLDER_PRODUCT_IMAGE);
        product.setVendor(vendor);
        product.setThumbnail(productDto.getMultipartFile().getOriginalFilename());
        return (ProductDto) Convert.PRO.toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> searchProductsByName(String name) {
        List<Product> products = productRepository
                .findAllByLanguageNameEnOrNameVn(name.toLowerCase());
        return products.stream()
                .map(entity -> (ProductDto) Convert.PRO.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream()
                .map((entity) -> (ProductDto) Convert.PRO.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchProductsByNameAndCategoryId(String name,
                                                              Long categoryId,
                                                              int page,
                                                              Sort sort) {
        return searchByCondition(name, categoryId, page, sort);
    }

    @Override
    public List<ProductDto> searchProductsByNameAndCategoryId(String name, Long categoryId, int page) {
        return searchByCondition(name, categoryId, page, null);
    }

    private List<ProductDto> searchByCondition(String name, Long categoryId, int page, Sort sort) {
        Pageable paging;
        if (sort == null) paging = PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM);
        else paging = PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM, sort);
        Page<Product> pages = productRepository
                .findAllByLanguageNameEnOrNameVnAAndCategory(name, categoryId, paging);
        SystemUtils.totalPage = pages.getTotalPages();
        List<Product> products = pages.getContent();
        return products.stream()
                .map((entity) -> (ProductDto) Convert.PRO.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByVendor() {
        String username = SecurityUtils.username();
        return productRepository.findAllByVendorUserUsername(username)
                .stream()
                .map(e -> (ProductDto) Convert.PRO.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByVendor(Long vendorId, Integer page, Sort sort) {
        Pageable paging = null;
        if (sort == null) paging = PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM);
        else paging = PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM, sort);
        Page<Product> productPages = productRepository.findAllByVendorId(vendorId, paging);
        SystemUtils.totalPage = productPages.getTotalPages();
        return productPages.getContent()
                .stream()
                .map(e -> (ProductDto) Convert.PRO.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAll(int page, Sort sort) {
        Pageable paging = null;
        if (sort == null) paging = PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM);
        else paging = PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM, sort);
        Page<Product> productPages = productRepository.findAll(paging);
        SystemUtils.totalPage = productPages.getTotalPages();
        return productPages.getContent()
                .stream()
                .map(e -> (ProductDto) Convert.PRO.toDto(e))
                .collect(Collectors.toList());
    }


}
