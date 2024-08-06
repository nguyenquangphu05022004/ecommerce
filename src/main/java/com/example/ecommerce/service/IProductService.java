package com.example.ecommerce.service;

import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.model.binding.InventoryRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductDetailsViewModel;
import com.example.ecommerce.domain.model.modelviews.product.ProductGalleryModelView;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.service.request.FilterInputRequestProduct;
import com.example.ecommerce.service.response.APIListResponse;

import java.util.List;
import java.util.Map;

public interface IProductService {
    ProductDetailsViewModel save(ProductRequest request);
    ProductDetailsViewModel findById(Long id);
    void delete(Long id);
    APIListResponse<ProductGalleryModelView> searchProduct(FilterInputRequestProduct filterInputProduct);
    ProductInventoryModelView getInventory(InventoryRequest request);
    APIListResponse<ProductGalleryModelView>  productRecommendation(Long id);
    APIListResponse<ProductGalleryModelView> filterProduct(Map<String, String> filter);
}
