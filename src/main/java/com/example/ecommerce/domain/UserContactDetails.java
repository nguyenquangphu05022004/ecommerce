package com.example.ecommerce.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Embeddable
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContactDetails {
    @Length(min = 10)
    private String fullName;
    @Length(min = 10)
    private String phoneNumber;
    @NotEmpty
    private String address;
    @NotEmpty
    private String province;
    @NotEmpty
    private String district;
    @NotEmpty
    private String ward;
}
