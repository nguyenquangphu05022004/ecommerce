package com.example.ecommerce.controller;

import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.request.FilterInputRequestProduct;
import com.example.ecommerce.service.request.ProductRequest;
import com.example.ecommerce.service.response.OperationResponse;
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
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
        productService.save(request);
        return new ResponseEntity<>(
                new OperationResponse(true, "Product is created"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "9") int limit
    ) {
        return ResponseEntity.ok(productService.findAll(page - 1, limit));
    }

    @PostMapping("/search")
    public ResponseEntity<?> getAllProduct(
            @RequestBody FilterInputRequestProduct filter
    ) {
        return ResponseEntity.ok(productService.searchProduct(filter));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getAllByCategoryId(
            @PathVariable("id") Long id,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit
    ) {
        return ResponseEntity.ok(productService.findAllByCategoryId(id, page - 1, limit));
    }
}
