package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.CategoryRequest;
import com.example.ecommerce.service.ICategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.version}" + "/categories")
@CrossOrigin("*")
public class CategoryController {
    private final ICategoryService categoryService;
    private final ObjectMapper objectMapper;
    @PostMapping
    public ResponseEntity<?> createCategory(
            @RequestParam("categoryRequest") String request,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws JsonProcessingException {
        CategoryRequest categoryRequest = objectMapper.readValue(request, new TypeReference<CategoryRequest>() {});
        categoryRequest.setFile(file);
        return ResponseEntity.ok(categoryService.save(categoryRequest));
    }

    @GetMapping
    public ResponseEntity<?> getAllCategoryParent(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "limit", defaultValue = "10") int limit) {
        return ResponseEntity.ok(categoryService.getAllCategoryParent(page, limit));
    }
}
