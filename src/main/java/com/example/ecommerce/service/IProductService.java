package com.example.ecommerce.service;

import com.example.ecommerce.service.dto.SortProductType;
import com.example.ecommerce.service.dto.ProductDto;
import com.example.ecommerce.service.request.FilterInputRequestProduct;
import com.example.ecommerce.service.request.ProductRequest;

import java.util.List;

public interface IProductService {
    void save(ProductRequest request);
    ProductDto findById(Long id);
     void delete(Long id);
    List<ProductDto> findProductByCategoryId(Long categoryId, int page);
    List<ProductDto> findAllByVendor();
    List<ProductDto> findAll(int page, int limit);
    List<ProductDto> findAllByVendorId(Long id);
    boolean productWasBoughtByUser(Long productId, String username);
    List<ProductDto> searchProduct(FilterInputRequestProduct filterInputProduct);

}
