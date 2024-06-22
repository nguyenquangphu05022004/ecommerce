package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.SortProductType;
import com.example.ecommerce.domain.dto.ProductDto;
import com.example.ecommerce.domain.dto.ProductRequest;

import java.util.List;

public interface IProductService extends IGenericService<ProductDto> {
    void saveOrUpdate(ProductRequest request);
    List<ProductDto> findProductByCategoryId(Long categoryId, int page);
    List<ProductDto> findAllByVendor();
    List<ProductDto> findAll(int page, int numberOfItem);

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
