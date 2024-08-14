package com.example.ecommerce.domain.response;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIListResponse <T>{
    private String message;
    private int error;
    private int ok;
    private int status;
    private Integer page;
    private Integer limit;
    private Integer totalPage;
    private List<T> data;
}
