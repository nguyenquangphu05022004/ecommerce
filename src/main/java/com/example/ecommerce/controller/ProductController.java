package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.InventoryRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductDetailsViewModel;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.request.FilterInputRequestProduct;
import com.example.ecommerce.service.response.OperationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        ProductDetailsViewModel response = productService.save(request);
        return ResponseEntity.ok(response);
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
            @RequestBody FilterInputRequestProduct filter
    ) {
        return ResponseEntity.ok(productService.searchProduct(filter));
    }

    @GetMapping("/inventories")
    public ProductInventoryModelView getInventoryAttributeKey(
            @RequestBody InventoryRequest request
            ) {
        return productService.getInventory(request);
    }
}
