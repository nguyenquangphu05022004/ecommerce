package com.example.ecommerce.system.dal.dataobject.user;

import lombok.Data;

@Data
public class Address {
    private String district;
    private String province;
    private String city;
    private String details;

}
