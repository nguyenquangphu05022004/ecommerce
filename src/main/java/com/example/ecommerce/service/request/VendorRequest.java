package com.example.ecommerce.service.request;

import lombok.Data;

@Data
public class VendorRequest extends RegisterRequest{
    private String shopName;
    private Integer perMoneyDelivery;
}
