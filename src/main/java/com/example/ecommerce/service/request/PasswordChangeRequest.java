package com.example.ecommerce.service.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordChangeRequest {
    private String oldPassword;
    private String newPassword;
}
