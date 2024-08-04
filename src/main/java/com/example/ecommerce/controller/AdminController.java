package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.BrandRequest;
import com.example.ecommerce.domain.model.binding.CategoryRequest;
import com.example.ecommerce.service.IBrandService;
import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class AdminController {
    private final IBrandService brandService;
    private final ICategoryService categoryService;

    @PostMapping("/admin/product-brands")
    public OperationResponse createProductBrand(@RequestBody BrandRequest request) {
        brandService.createBrand(request);
        return new OperationResponse(
                true,
                "You created brand",
                200
        );
    }
    @PostMapping("/admin/product-categories")
    public OperationResponse createProductCategory(@RequestBody CategoryRequest request) {
        categoryService.save(request);
        return new OperationResponse(
                true,
                "You created category",
                200
        );
    }
    @GetMapping("/brands")
    public ResponseEntity<?> getListBrand() {
        return ResponseEntity.ok(brandService.getAllBrand());
    }
    @GetMapping("/categories")
    public ResponseEntity<?> getListCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
}
