package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.StockRequest;
import com.example.ecommerce.dto.StockResponse;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StockController {
    private final IStockService stockService;
    private final IProductService productService;
    @GetMapping("/vendor/products/stock/add")
    public String getFormCreateStockForProduct(Model model) {
        List<ProductDto> listProductOfVendor = productService.findAllByVendor();
        model.addAttribute("products", listProductOfVendor);
        model.addAttribute("stockRequest", new StockRequest());
        return "admin/product/create-stock";
    }
    @PostMapping("/vendor/products/stock/add")
    public String createStockForProduct(@ModelAttribute StockRequest stockRequest,
                                        @RequestParam("files")List<MultipartFile> files) {
        stockRequest.setMultipartFiles(files);
        stockService.save(stockRequest);
        return "redirect:/stocks/add";
    }
    @GetMapping("/stocks/{stockId}")
    @ResponseBody
    public StockResponse getById(@PathVariable("stockId") Long stockId) {
        return stockService.findById(stockId);
    }

}
