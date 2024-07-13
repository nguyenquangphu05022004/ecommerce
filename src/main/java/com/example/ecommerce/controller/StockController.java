package com.example.ecommerce.controller;


import com.example.ecommerce.service.IStockService;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.request.StockRequest;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/stocks")
public class StockController {

    private final IStockService stockService;

    @PostMapping
    public ResponseEntity<?> createStock(
            @RequestPart("request") StockRequest request,
            @RequestPart("files") List<MultipartFile> files
            ) {
        request.setFileImages(files);
        stockService.save(request);
        return new ResponseEntity<>(
                new OperationResponse(true, "stock is created successfully"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockDto> getStockById(@PathVariable("stockId") Long stockId) {
        return ResponseEntity.ok(stockService.findById(stockId));
    }

}
