package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.product.DecorationResponse;
import com.example.ecommerce.domain.dto.product.ProductDto;
import com.example.ecommerce.domain.dto.product.StockRequest;
import com.example.ecommerce.domain.dto.product.StockResponse;
import com.example.ecommerce.service.IDecorationService;
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
    private final IDecorationService decorationService;
    @GetMapping("/vendor/products/stock/add")
    public String getFormCreateStockForProduct(Model model) {
        List<ProductDto> listProductOfVendor = productService.findAllByVendor();
        List<DecorationResponse> decorationResponseList = decorationService.listDecoration();
        model.addAttribute("decorations", decorationResponseList);
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
