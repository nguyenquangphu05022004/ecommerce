package com.example.ecommerce.service.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIListResponse <T>{
    private String message;
    private String error;
    private int ok;
    private int status;
    private int page;
    private int limit;
    private int totalPage;
    private List<T> data;
}
