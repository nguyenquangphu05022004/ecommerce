package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.CartRequest;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.service.ICartService;
import com.example.ecommerce.domain.response.OperationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/shopping-cart")
@CrossOrigin("*")

public class ShoppingCartController {
    private final ICartService cartService;

    @PostMapping("/products/inventories")
    public APIResponse<?> addProduct(@RequestBody @Valid CartRequest request) {
        return cartService.add(request);
    }

    @GetMapping("/products/inventories")
    public APIListResponse<?> getShoppingCart() {
        return cartService.getShoppingCart();
    }
    @DeleteMapping("/products/inventories")
    public APIResponse<?> deleteProduct(
            @RequestParam("inventoryId") Long inventoryId,
            @RequestParam("vendorId") Long vendorId) {
        return cartService.delete(inventoryId, vendorId);
    }
}
