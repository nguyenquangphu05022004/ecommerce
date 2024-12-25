package com.example.ecommerce.system.controller.admin.user.vo;

import com.example.ecommerce.system.dal.dataobject.user.Address;
import lombok.Data;

@Data
public class AddressResVO {
    private Long id;
    private Boolean defaultAddress;
    private String district;
    private String province;
    private String city;
    private String details;

    public AddressResVO(Address address) {
        this.id = address.getId();
        this.defaultAddress = address.getDefaultAddress();
        this.district = address.getDistrict();
        this.province = address.getProvince();
        this.city = address.getCity();
        this.details = address.getDetails();
    }
}
