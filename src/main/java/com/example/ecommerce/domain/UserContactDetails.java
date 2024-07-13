package com.example.ecommerce.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContactDetails {
    private String fullName;
    private String phoneNumber;
    private String address;
    private String province;
    private String district;
    private String ward;
}
