package com.example.ecommerce.production.controller.brand;

import com.example.ecommerce.production.service.brand.ProductBrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brands")
@Tag(name = "Product Brand")
@CrossOrigin("*")
public class ProductBrandController {
    private final ProductBrandService productBrandService;
}
