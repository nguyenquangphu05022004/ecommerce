package com.example.ecommerce.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OperationResponse {
    private boolean success;
    private String message;
}
