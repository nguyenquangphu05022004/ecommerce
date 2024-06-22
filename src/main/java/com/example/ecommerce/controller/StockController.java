package com.example.ecommerce.controller;
import com.example.ecommerce.domain.Color;
import com.example.ecommerce.domain.dto.ProductDto;
import com.example.ecommerce.domain.dto.StockRequest;
import com.example.ecommerce.domain.dto.StockResponse;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class StockController {
    private final IStockService stockService;
    private final IProductService productService;
    @GetMapping("/vendor/products/stock/add")
    public String getFormCreateStock(Model model) {
        List<ProductDto> listProductOfVendor = productService.findAllByVendor();
        model.addAttribute("colors", Arrays.asList(Color.values()));
        model.addAttribute("products", listProductOfVendor);
        model.addAttribute("stockRequest", new StockRequest());
        return "admin/product/create-stock";
    }
    @GetMapping("/vendor/products/stock/edit/{stockId}")
    public String getFormEditStock(Model model, @PathVariable("stockId") Long stockId) {
        StockResponse stockResponse = stockService.findById(stockId);
        StockRequest stockRequest = StockRequest.builder()
                .id(stockId)
                .price(stockResponse.getPrice())
                .color(stockResponse.getColor())
                .code(stockResponse.getCode())
                .productId(stockResponse.getProduct().getId())
                .formatClassification(stockResponse.getStockClassifications()
                        .stream()
                        .map(st -> st.getQuantityOfProduct() + ";" + st.getSize().name())
                        .collect(Collectors.joining(",")))
                .build();
        model.addAttribute("stock", stockRequest);
        model.addAttribute("images", stockResponse.getImages());
        model.addAttribute("colors", Arrays.asList(Color.values()));
        return "admin/product/stock-edit";
    }
    @PostMapping("/vendor/products/stock/edit/{stockId}")
    public String updateStock(@ModelAttribute StockRequest stockRequest) {
        stockService.save(stockRequest);
        return "redirect:/stocks/add";
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
