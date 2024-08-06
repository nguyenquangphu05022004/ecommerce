package com.example.ecommerce.service.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VendorRequest extends RegisterRequest{
    private String shopName;
    private Integer perMoneyDelivery;
}
