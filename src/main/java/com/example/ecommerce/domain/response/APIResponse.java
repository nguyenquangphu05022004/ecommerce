package com.example.ecommerce.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse <T>{
    private int status;
    private T data;
    private String message;
}
