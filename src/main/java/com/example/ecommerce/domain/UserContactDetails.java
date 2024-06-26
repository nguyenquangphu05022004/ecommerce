package com.example.ecommerce.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
public class UserContactDetails {
    private String fullName;
    private String phoneNumber;
    private String address;
    private String province;
    private String district;
    private String ward;
}
