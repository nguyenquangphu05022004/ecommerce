package com.example.ecommerce.controller;


import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.dto.ENUM.SortProductType;
import com.example.ecommerce.domain.dto.product.CategoryDto;
import com.example.ecommerce.domain.dto.product.EvaluationDto;
import com.example.ecommerce.domain.dto.product.ProductDto;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.impl.CategoryServiceImpl;
import com.example.ecommerce.utils.SortUtils;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;
    private final CategoryServiceImpl categoryService;



    @GetMapping("/vendor/products/add")
    public String getFormProduct(Model model) {
        ProductDto productDto = new ProductDto();
        List<CategoryDto> categoryDtos = categoryService.records();
        model.addAttribute("product", productDto);
        model.addAttribute("categories", categoryDtos);
        return "admin/product/create-products";
    }

    @PostMapping("/vendor/products/add")
    @ResponseBody
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.saveOrUpdate(productDto);
    }

    @GetMapping("/vendor/product/{productId}/images/upload")
    public String formUploadThumbnails(@PathVariable("productId") Long productId, Model model) {
        model.addAttribute("productId", productId);
        return "admin/product/upload-images";
    }

    @GetMapping("/vendor/products")
    public String getListProductOfVendor(Model model) {
        List<ProductDto> productDtos = productService.findAllByVendor();
        model.addAttribute("products", productDtos);
        return "admin/product/list-products";
    }

    @GetMapping("/products/{productId}/**")
    public String getProductById(@PathVariable("productId") Long productId,
                                 Model model) {
        ProductDto productDto = productService.findById(productId);
        boolean wasBoughtByUser = productService.productWasBoughtByUser(productId, SecurityUtils.username());
        model.addAttribute("evaluation", new EvaluationDto());
        model.addAttribute("was_bought", wasBoughtByUser);
        model.addAttribute("product", productDto);
        model.addAttribute("products", productService.findProductByCategoryId(productDto.getCategory().getId(), 0));
        return "product";
    }

    @GetMapping("/products")
    @ResponseBody
    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = productService.records();
        return products;
    }

    @GetMapping("/shop/search/products")
    public String searchProducts(
            @RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
            @RequestParam(value = "vendorId", defaultValue = "0") Long vendorId,
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "startPrice", defaultValue = "0") Integer startPrice,
            @RequestParam(value = "endPrice", defaultValue = "0") Integer endPrice,
            @RequestParam(value="page", defaultValue = "1") int page,
            @RequestParam(value = "sortProductType", defaultValue = "DEFAULT") SortProductType sortProductType,
            Model model
    ) {
        List<ProductDto> products = productService.searchProduct(
                categoryId,
                vendorId,
                query,
                startPrice,
                endPrice,
                sortProductType,
                page - 1
        );
        saveAttribute(products,vendorId,
                query, categoryId,
                page, startPrice, endPrice,
                sortProductType.name(), model);
        return "shop";
    }

    private void saveAttribute(
            List<ProductDto> productDtos,
            Long vendorId, String query,
            Long categoryId, int page,
            int startPrice, int endPrice,
            String sortProductType,
            Model model
    ) {
        List<CategoryDto> categoryDtos = categoryService.records();
        model.addAttribute("vendorId", vendorId);
        model.addAttribute("products", productDtos);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryDtos);
        model.addAttribute("keyword", query);
        model.addAttribute("totalPage", SystemUtils.totalPage);
        model.addAttribute("startPrice", startPrice);
        model.addAttribute("endPrice", endPrice);
        model.addAttribute("sortProductType", sortProductType);
        model.addAttribute("page", page - 1);
    }

}