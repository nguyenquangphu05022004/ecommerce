package com.example.ecommerce.system.controller.app.user.vo;

import com.example.ecommerce.system.dal.dataobject.user.Address;
import lombok.Data;

@Data
public class AddressResVO {
    private Long id;
    private Boolean defaultAddress;
    private String city;
    private String district;
    private String commune;

    private String detailAddress;

    private String fullName;
    private String phoneNumber;

    private String fullAddress;


    public AddressResVO(Address address) {
        this.id = address.getId();
        this.defaultAddress = address.getDefaultAddress();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.commune = address.getCommune();
        this.detailAddress = address.getDetailAddress();
        this.fullName = address.getFullName();
        this.phoneNumber = address.getPhoneNumber();
        this.fullAddress = address.fullAddress();
    }
}
