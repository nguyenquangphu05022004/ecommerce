package com.example.ecommerce.service;

import com.example.ecommerce.domain.model.binding.ProductInventoryFilterRequest;
import com.example.ecommerce.domain.model.binding.ProductInventoryRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.domain.response.APIResponse;

public interface IProductInventoryService {
    APIResponse<ProductInventoryModelView> createProductInventory(ProductInventoryRequest request);
    APIResponse<ProductInventoryModelView> getProductInventory(ProductInventoryFilterRequest request);
    void delete(Long inventoryId);
}
