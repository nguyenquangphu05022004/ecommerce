package com.example.ecommerce.controller;


import com.example.ecommerce.domain.dto.CategoryDto;
import com.example.ecommerce.domain.dto.ProductDto;
import com.example.ecommerce.domain.dto.ProductRequest;
import com.example.ecommerce.service.ICategoryService;
import com.example.ecommerce.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;
    private final ICategoryService categoryService;



    @GetMapping("/vendor/products/add")
    public String getFormProduct(Model model) {
        model.addAttribute("categories", getListCategory());
        return "admin/product/create-products";
    }

    @PostMapping("/vendor/products/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request) {
         productService.saveOrUpdate(request);
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



    @GetMapping("/products")
    @ResponseBody
    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = productService.getAll();
        return products;
    }

//    @GetMapping("/shop/search/products")
//    public String searchProducts(
//            @RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
//            @RequestParam(value = "vendorId", defaultValue = "0") Long vendorId,
//            @RequestParam(value = "query", defaultValue = "") String query,
//            @RequestParam(value = "startPrice", defaultValue = "0") Integer startPrice,
//            @RequestParam(value = "endPrice", defaultValue = "0") Integer endPrice,
//            @RequestParam(value="page", defaultValue = "1") int page,
//            @RequestParam(value = "sortProductType", defaultValue = "DEFAULT") SortProductType sortProductType,
//            Model model
//    ) {
//        List<ProductDto> products = productService.searchProduct(
//                categoryId,
//                vendorId,
//                query,
//                startPrice,
//                endPrice,
//                sortProductType,
//                page - 1
//        );
//        model.addAttribute("sortTypes", Arrays.asList(SortProductType.values()));
//        saveAttribute(products,vendorId,
//                query, categoryId,
//                page, startPrice, endPrice,
//                sortProductType.name(), model);
//        return "shop";
//    }
//
//    private void saveAttribute(
//            List<ProductDto> productDtos,
//            Long vendorId, String query,
//            Long categoryId, int page,
//            int startPrice, int endPrice,
//            String sortProductType,
//            Model model
//    ) {
//        model.addAttribute("vendorId", vendorId);
//        model.addAttribute("products", productDtos);
//        model.addAttribute("categoryId", categoryId);
//        model.addAttribute("categories", getListCategory());
//        model.addAttribute("keyword", query);
//        model.addAttribute("totalPage", SystemUtils.totalPage);
//        model.addAttribute("startPrice", startPrice);
//        model.addAttribute("endPrice", endPrice);
//        model.addAttribute("sortProductType", sortProductType);
//        model.addAttribute("page", page - 1);
//    }

    private List<CategoryDto> getListCategory() {
        return categoryService.getAll();
    }

}