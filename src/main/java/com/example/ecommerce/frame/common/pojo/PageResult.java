package com.example.ecommerce.frame.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@Schema(name = "Phan chia trang - PageResult")
public class PageResult <T>{
    @Schema(description = "Trang hien tai", example = "1")
    private Integer currentPage;
    @Schema(description = "Tong so trang", example = "20")
    private Integer totalPage;

    @Schema(description = "Danh sach item")
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
