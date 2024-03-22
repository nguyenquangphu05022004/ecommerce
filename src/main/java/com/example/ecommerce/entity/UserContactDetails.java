package com.example.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;

@Embeddable
@Data
@Getter
public class UserContactDetails {
    private String fullName;
    private String phoneNumber;
    private String address;
    private String province;
    private String district;
    private String ward;
}
