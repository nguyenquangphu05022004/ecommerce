package com.example.ecommerce.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse <T>{
    private String message;
    private String error;
    private int ok;
    private int status;
    private T data;
}
