package com.example.ecommerce.controller;


import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.SortProductType;
import com.example.ecommerce.service.impl.CategoryServiceImpl;
import com.example.ecommerce.service.impl.ProductServiceImpl;
import com.example.ecommerce.utils.SortUtils;
import com.example.ecommerce.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        model.addAttribute("product", productDto);
        return "product";
    }

    @GetMapping("/products/category/{categoryId}")
    @ResponseBody
    public List<ProductDto> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductDto> products = productService.findProductByCategoryId(categoryId);
        return products;
    }

    @GetMapping("/products")
    @ResponseBody
    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = productService.records();
        return products;
    }


    @GetMapping("/shop/products/category/{categoryId}/search")
    public String getAllProductsByKeywordNameAndCategory(
            @RequestParam("query") String query,
            @PathVariable("categoryId") Long categoryId,
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            Model model) {
        getListProductBySearchCondition(query, categoryId, page, model, -1l);
        return "shop";
    }

    @GetMapping("/shop/vendor/{vendorId}")
    public String getListProductOfVendorById(@PathVariable Long vendorId,
                                             @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                             Model model) {
        List<CategoryDto> categoryDtos = categoryService.records();
        Sort sortAble = null;
        List<ProductDto> products = productService.findAllByVendor(vendorId, page - 1, sortAble);
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryDtos);
        model.addAttribute("totalPage", SystemUtils.totalPage);
        model.addAttribute("page", page - 1);
        return "shop";
    }

    @GetMapping("/shop/vendor/{vendorId}/products/category/{categoryId}/search")
    public String getListProductOfVendorByIdAndSearch(@PathVariable Long vendorId,
                                                      @RequestParam("query") String query,
                                                      @PathVariable("categoryId") Long categoryId,
                                                      @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                      Model model) {
        getListProductBySearchCondition(query, categoryId, page , model, vendorId);
        return "shop";
    }

    @PostMapping("/products/sort/**")
    @ResponseBody
    public List<ProductDto> sortProduct(@RequestParam("type") SortProductType type,
                                        @RequestBody List<ProductDto> products
                                        ) {
       return  SortUtils.getListAfterSort(type, products);
    }


    
    private void getListProductBySearchCondition(
            String query,
            Long categoryId,
            int page,
            Model model,
            Long vendorId
    ) {
        List<CategoryDto> categoryDtos = categoryService.records();
        List<ProductDto> productDtos = new ArrayList<>();
        if (query.isEmpty() && categoryId == 0) productDtos = productService.records();
        else if (query.isEmpty() && categoryId != 0) productDtos = productService.findProductByCategoryId(categoryId);
        else if (!query.isEmpty() && categoryId == 0) productDtos = productService.searchProductsByName(query);
        else productDtos = productService.searchProductsByNameAndCategoryId(query, categoryId, page - 1);
        if(vendorId > 0) {
            List<ProductDto> products = new ArrayList<>();
            for(ProductDto pro : productDtos) {
                if(pro.getVendor().getId() == vendorId) {
                    products.add(pro);
                }
            }
            productDtos = products;
        }
        model.addAttribute("products", productDtos);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryDtos);
        model.addAttribute("keyword", query);
        model.addAttribute("totalPage", SystemUtils.totalPage);
        model.addAttribute("page", page - 1);
    }

}
