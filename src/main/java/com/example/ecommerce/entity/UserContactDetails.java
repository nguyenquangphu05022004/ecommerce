package com.example.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserContactDetails {
    private String fullName;
    private String phoneNumber;
    private String address;
    private String province;
    private String district;
    private String ward;
}
