package com.example.ecommerce.controller;

import com.example.ecommerce.service.ICartService;
import com.example.ecommerce.service.request.CartRequest;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shopping-cart")
public class ShoppingCartController {
    private final ICartService cartService;

    @PostMapping("/products/stocks")
    public ResponseEntity<?> addProduct(@RequestBody CartRequest request) {
        cartService.add(request);
        return ResponseEntity.ok(
                OperationResponse.builder()
                        .success(true)
                        .message("A product is added to cart")
                        .build()
        );
    }

    @GetMapping("/products/stocks")
    public ResponseEntity<?> getShoppingCart() {
        return ResponseEntity.ok(cartService.getShoppingCart());
    }
    @DeleteMapping("/products/stocks/{stockId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("stockId") Long stockId) {
        cartService.delete(stockId);
        return ResponseEntity.ok(
                OperationResponse.builder()
                        .success(true)
                        .message(String.format("Stock with id %s is deleted", stockId))
                        .build()
        );
    }
}
