package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.ProductFilterRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.version}" + "/products")
@CrossOrigin("*")

public class ProductController {
    private final IProductService productService;

    @PostMapping
    public APIResponse<?> createProduct(@RequestBody @Valid ProductRequest request) {
        return productService.save(request);
    }

    @GetMapping("/{id}/{slug}")
    public APIResponse<?> getProductById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @PostMapping("/search")
    public APIListResponse<?> getAllProduct(
            @RequestBody ProductFilterRequest filter
    ) {
        return productService.productFilter(filter);
    }
}
