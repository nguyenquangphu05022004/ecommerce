package com.example.ecommerce.domain.response;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class APIListResponse <T>{
    private String message;
    private int status;
    private Integer page;
    private Integer limit;
    private Integer totalPage;
    private List<T> data;

    public APIListResponse(int status, Integer page,
                           Integer limit, Integer totalPage,
                           List<T> data) {
        this.status = status;
        this.page = page;
        this.limit = limit;
        this.totalPage = totalPage;
        this.data = data;
    }

    public APIListResponse(String message, int status,
                           Integer page, Integer limit,
                           Integer totalPage, List<T> data) {
        this.message = message;
        this.status = status;
        this.page = page;
        this.limit = limit;
        this.totalPage = totalPage;
        this.data = data;
    }
}
