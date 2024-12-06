package com.example.ecommerce.product.controller.spu.self.vo;

import lombok.Data;

import java.util.Map;

@Data
public class ProductSpuSearchReqVO {
    private Map<String, String> map;
    private boolean sort;
    private String sortField;
    private int sortType; //asc: 0; desc: 1
    private int page;
}
