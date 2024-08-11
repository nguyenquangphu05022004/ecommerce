package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.BrandRequest;
import com.example.ecommerce.domain.model.binding.CategoryRequest;
import com.example.ecommerce.service.IBrandService;
import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.domain.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public OperationResponse createProductCategory(
            @RequestPart("cateRequest") CategoryRequest request,
            @RequestParam("file")MultipartFile file
            ) {
        request.setFile(file);
        categoryService.save(request);
        return new OperationResponse(
                true,
                "You created category",
                200
        );
    }
}
