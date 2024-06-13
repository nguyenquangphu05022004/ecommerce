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


    @GetMapping("/shop/products/search")
    public String getListProductBySearchWithoutVendor(
            @RequestParam(value = "query", defaultValue = "", required = false) String query,
            @RequestParam(value = "categoryId", defaultValue = "0", required = false) Long categoryId,
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name="startPrice", defaultValue = "0", required = false) Integer startPrice,
            @RequestParam(name = "endPrice", defaultValue = "0", required = false) Integer endPrice,
            Model model) {
        query = query.isEmpty() ? null : query;
        categoryId = categoryId == null || categoryId == 0 ? 0 : categoryId;
        startPrice = startPrice == null || startPrice == 0? 0 : startPrice;
        endPrice = endPrice == null || endPrice == 0 ? 0 : endPrice;
        List<ProductDto> search = productService.search(
                query, categoryId,null,
                Math.min(startPrice, endPrice),
                Math.max(startPrice, endPrice),page - 1);
        saveAttribute(search,  query, categoryId, page, startPrice, endPrice, model);
        return "shop";
    }

    @GetMapping("/shop/vendor/{vendorId}/search")
    public String getListProductOfVendorById(@PathVariable Long vendorId,
                                             @RequestParam(value = "query", defaultValue = "", required = false) String query,
                                             @RequestParam(value = "categoryId", required = false) Long categoryId,
                                             @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                             @RequestParam(name="startPrice", defaultValue = "0", required = false) Integer startPrice,
                                             @RequestParam(name = "endPrice", defaultValue = "0", required = false) Integer endPrice,
                                             Model model) {
        query = query.isEmpty() ? null : query;
        categoryId = categoryId == null || categoryId == 0 ? 0 : categoryId;
        startPrice = startPrice == null || startPrice == 0? 0 : startPrice;
        endPrice = endPrice == null || endPrice == 0 ? 0 : endPrice;
        List<ProductDto> products = productService.search(
                query, categoryId,vendorId,
                Math.min(startPrice, endPrice),
                Math.max(startPrice, endPrice),page - 1);
        saveAttribute(products,  query, categoryId, page, startPrice, endPrice, model);
        return "shop";
    }


    @PostMapping("/products/sort/**")
    @ResponseBody
    public List<ProductDto> sortProduct(@RequestParam("type") SortProductType type,
                                        @RequestBody List<ProductDto> products
    ) {
        return  SortUtils.sortProduct(type, products);
    }



    private void saveAttribute(
            List<ProductDto> productDtos,
            String query, Long categoryId, int page,
            int startPrice, int endPrice, Model model
    ) {
        List<CategoryDto> categoryDtos = categoryService.records();
        model.addAttribute("products", productDtos);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryDtos);
        model.addAttribute("keyword", query);
        model.addAttribute("totalPage", SystemUtils.totalPage);
        model.addAttribute("startPrice", startPrice);
        model.addAttribute("endPrice", endPrice);
        model.addAttribute("page", page - 1);
    }

}