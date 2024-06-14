package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.ENUM.SortProductType;
import com.example.ecommerce.domain.dto.product.ProductDto;

import java.util.List;

public interface IProductService extends IGenericService<ProductDto> {
    ProductDto saveOrUpdate(ProductDto productDto);
    List<ProductDto> findProductByCategoryId(Long categoryId, int page);
    List<ProductDto> findAllByVendor();
    List<ProductDto> findAll(int page);

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
