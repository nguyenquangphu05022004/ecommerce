package com.example.ecommerce.service;

import com.example.ecommerce.service.dto.SortProductType;
import com.example.ecommerce.service.dto.ProductDto;
import com.example.ecommerce.service.request.FilterInputRequestProduct;
import com.example.ecommerce.service.request.ProductRequest;
import com.example.ecommerce.service.response.APIListResponse;

import java.util.List;

public interface IProductService {
    void save(ProductRequest request);
    ProductDto findById(Long id);
     void delete(Long id);
    APIListResponse<ProductDto> findProductByCategoryId(Long categoryId, int page, int limit);
    APIListResponse<ProductDto> findAllByVendor(int page, int limit);
    APIListResponse<ProductDto> findAll(int page, int limit);
    APIListResponse<ProductDto> findAllByVendorId(Long id, int page, int limit);
    boolean productWasBoughtByUser(Long productId, String username);
    APIListResponse<ProductDto> searchProduct(FilterInputRequestProduct filterInputProduct);

}
