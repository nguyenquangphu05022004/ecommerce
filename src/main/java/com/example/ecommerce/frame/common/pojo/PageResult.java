package com.example.ecommerce.frame.common.pojo;

import java.util.List;

public class PageResult <T>{
    private Integer currentPage;
    private Integer totalPage;
    private List<T> list;
}
