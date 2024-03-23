package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService extends IGenericService<ProductDto> {
    ProductDto saveOrUpdate(ProductDto productDto);
    List<ProductDto> searchProductsByName(String name);
    List<ProductDto> findProductByCategoryId(Long categoryId);
    List<ProductDto> searchProductsByNameAndCategoryId(String name, Long categoryId, int page, Sort sort);
    List<ProductDto> searchProductsByNameAndCategoryId(String name, Long categoryId, int page);
    List<ProductDto> findAllByVendor();
    List<ProductDto> findAllByVendor(Long vendorId, Integer page, Sort sort);
    List<ProductDto> findAll(int page, Sort sort);

}
