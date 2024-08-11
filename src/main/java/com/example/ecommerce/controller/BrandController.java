package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.BrandRequest;
import com.example.ecommerce.service.IBrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/brands")
@CrossOrigin("*")
public class BrandController {
    private final IBrandService brandService;
    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody @Valid BrandRequest request) {
        return ResponseEntity.ok( brandService.createBrand(request));
    }

    @GetMapping
    public ResponseEntity<?> getAllBrand(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "limit", defaultValue = "20") int limit) {
        return ResponseEntity.ok(brandService.getAllBrand(page, limit));
    }
 }
