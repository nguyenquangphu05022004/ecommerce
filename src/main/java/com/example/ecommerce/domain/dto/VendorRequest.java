package com.example.ecommerce.domain.dto;

import lombok.Data;

@Data
public class VendorRequest {
    private String username;
    private String shopName;
    private Integer perMoneyDelivery;

}
