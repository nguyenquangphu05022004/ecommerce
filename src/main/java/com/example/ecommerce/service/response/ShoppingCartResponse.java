package com.example.ecommerce.service.response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ShoppingCartResponse {
    private Set<VendorCartResponse> vendors;
    public ShoppingCartResponse() {
        vendors = new HashSet<>();
    }
}
