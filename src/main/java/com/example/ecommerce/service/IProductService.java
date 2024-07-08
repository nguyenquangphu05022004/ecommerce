package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.SortProductType;
import com.example.ecommerce.service.dto.ProductDto;
import com.example.ecommerce.service.request.ProductRequest;

import java.util.List;

public interface IProductService {
    void save(ProductRequest request);
    ProductDto findById(Long id);
    public void delete(Long id);
    List<ProductDto> findProductByCategoryId(Long categoryId, int page);
    List<ProductDto> findAllByVendor();
    List<ProductDto> findAll(int page, int numberOfItem);
    List<ProductDto> findAllByVendorId(Long id);
    boolean productWasBoughtByUser(Long productId, String username);
    List<ProductDto> searchProduct(
            Long categoryId,
            Long vendorId,
            String query,
            Integer startPrice,
            Integer endPrice,
            SortProductType sortProductType,
            int page
    );

}
