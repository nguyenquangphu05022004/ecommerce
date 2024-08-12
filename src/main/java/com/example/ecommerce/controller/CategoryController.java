package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.CategoryRequest;
import com.example.ecommerce.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@CrossOrigin("*")
public class CategoryController {
    private final ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.ok(categoryService.save(request));
    }
    @GetMapping
    public ResponseEntity<?> getAllCategoryParent(@RequestParam("page") int page,
                                                  @RequestParam("limit") int limit) {
        return ResponseEntity.ok(categoryService.getAllCategoryParent(page, limit));
    }
}
