package com.example.ecommerce.service.request;

import lombok.Data;

@Data
public class VendorRequest {
    private String username;
    private String shopName;
    private Integer perMoneyDelivery;
}
