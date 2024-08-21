package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.FilterProductRequest;
import com.example.ecommerce.domain.model.binding.InventoryRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductDetailsViewModel;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@CrossOrigin("*")

public class ProductController {
    private final IProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok( productService.save(request));
    }

    @GetMapping("/{id}/{slug}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/{id}/recommendation")
    public ResponseEntity<?> getAllProductRecommendation(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.productRecommendation(id));
    }

    @PostMapping("/search")
    public ResponseEntity<?> getAllProduct(
            @RequestBody FilterProductRequest filter
    ) {
        return ResponseEntity.ok(productService.filterProduct(filter));
    }

    @GetMapping("/inventories")
    public APIResponse<ProductInventoryModelView> getInventoryAttributeKey(
            @RequestBody InventoryRequest request
            ) {
        return productService.getInventory(request);
    }
}
