package com.example.ecommerce.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Class
public class EmailDetails {
    private String username;
    private String recipient;
    private String linkVerify;
    private String subject;
    private String attachment;
    private String code;
}