package com.example.ecommerce.frame.common.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResult <T>{
    private Integer currentPage;
    private Integer totalPage;
    private List<T> list;

    public PageResult(Page<T> page) {
        this.currentPage = page.getNumber() + 1;
        this.totalPage = page.getTotalPages();
        this.list = page.getContent();
    }
    public PageResult(int page, int totalPage, List<T> list) {
        this.currentPage = page +1;
        this.totalPage = totalPage;
        this.list = list;
    }
}
