package com.example.ecommerce.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
@Builder
public class OperationResponse  {
    private boolean success;
    private String message;
    private int statusValue;
}
