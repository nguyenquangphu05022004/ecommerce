package com.example.ecommerce.service;

import com.example.ecommerce.domain.model.binding.FilterProductRequest;
import com.example.ecommerce.domain.model.binding.ProductInventoryFilterRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductGalleryModelView;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;

public interface IProductService {
    APIResponse<?> save(ProductRequest request);
    APIResponse<?> findById(Long id);
    APIListResponse<ProductGalleryModelView>  productRecommendation(Long id);
    APIListResponse<ProductGalleryModelView> filterProduct(FilterProductRequest filterProductRequest);
}
