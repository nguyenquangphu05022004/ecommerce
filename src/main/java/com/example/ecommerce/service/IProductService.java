package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.product.ProductDto;

import java.util.List;

public interface IProductService extends IGenericService<ProductDto> {
    ProductDto saveOrUpdate(ProductDto productDto);
    List<ProductDto> findProductByCategoryId(Long categoryId, int page);
    List<ProductDto> search(String query, Long categoryId,
                            Long vendorId, Integer startPrice,
                            Integer endPrice, int page);
    List<ProductDto> findAllByVendor();
    List<ProductDto> findAllByVendor(Long vendorId, Integer page);
    List<ProductDto> findAll(int page);

    boolean productWasBoughtByUser(Long productId, String username);


}
