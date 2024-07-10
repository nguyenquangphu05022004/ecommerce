package com.example.ecommerce.controller;

import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.request.ProductRequest;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
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
}
