package com.example.ecommerce.controller;


import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.impl.CategoryServiceImpl;
import com.example.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductServiceImpl productService;
    private final CategoryServiceImpl categoryService;
    @Autowired
    public ProductController(ProductServiceImpl productService,
                             CategoryServiceImpl categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping("/admin/vendor/products/add")
    public String getFormProduct(Model model) {
        ProductDto productDto = new ProductDto();
        List<CategoryDto> categoryDtos = categoryService.records();
        model.addAttribute("product", productDto);
        model.addAttribute("categories", categoryDtos);
        return "admin/product/create-products";
    }
    @PostMapping("/admin/vendor/products/add")
    public String createProduct(Model model,
                                @RequestParam("categoryId") Long categoryId,
                                @ModelAttribute ProductDto productDto
                                ) {
        productService.saveOrUpdate(productDto, categoryId);
        return "redirect:/admin/vendor/products";
    }
    @GetMapping("/admin/vendor/products")
    public String createProduct(Model model) {
        List<ProductDto> productDtos = productService.records();
        model.addAttribute("products", productDtos);
        return "admin/product/list-products";
    }
    @GetMapping("/products/{productId}/**")
    public String getProductById(@PathVariable("productId") Long productId,
                                 Model model) {
        ProductDto productDto = productService.findById(productId);
        model.addAttribute("product", productDto);
        return "product";
    }
    @GetMapping("/category/products/{categoryId}")
    @ResponseBody
    public List<ProductDto> getProductsByCategoryId(@PathVariable Long categoryId) {
        return productService.findProductByCategoryId(categoryId);
    }
    @GetMapping("/products")
    @ResponseBody
    public List<ProductDto> getAllProducts() {
        return productService.records();
    }
}
