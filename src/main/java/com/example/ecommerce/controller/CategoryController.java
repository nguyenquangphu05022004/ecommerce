package com.example.ecommerce.controller;

import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.service.request.CategoryRequest;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
@RestController
public class CategoryController {

    private final ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createVendor(
            @RequestBody CategoryRequest request
    ) {
        categoryService.save(request);
        return new ResponseEntity<>(
                OperationResponse.builder()
                        .success(true)
                        .message("You created category successfully")
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        return new ResponseEntity<>(
                categoryService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getById(@PathVariable("categoryId") Long cateId) {
        return new ResponseEntity<>(
                categoryService.findById(cateId),
                HttpStatus.OK
        );
    }


}
