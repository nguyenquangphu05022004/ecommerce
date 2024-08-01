package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.CartRequest;
import com.example.ecommerce.service.ICartService;
import com.example.ecommerce.service.response.OperationResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shopping-cart")
@CrossOrigin("*")

public class ShoppingCartController {
    private final ICartService cartService;

    @PostMapping("/products/stocks")
    public ResponseEntity<?> addProduct(@RequestBody CartRequest request, HttpServletRequest servletRequest) {
        cartService.add(request, servletRequest);
        return ResponseEntity.ok(
                OperationResponse.builder()
                        .success(true)
                        .message("A product is added to cart")
                        .build()
        );
    }

    @GetMapping("/products/stocks")
    public ResponseEntity<?> getShoppingCart(HttpServletRequest servletRequest) {
        return ResponseEntity.ok(cartService.getShoppingCart(servletRequest));
    }
    @DeleteMapping("/products/stocks")
    public ResponseEntity<?> deleteProduct(
            @RequestParam("stockId") Long stockId,
            @RequestParam("vendorId") Long vendorId,
            HttpServletRequest servletRequest) {
        cartService.delete(stockId, vendorId, servletRequest);
        return ResponseEntity.ok(
                OperationResponse.builder()
                        .success(true)
                        .message(String.format("Stock with id %s is deleted", stockId))
                        .build()
        );
    }
}
