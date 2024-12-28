package com.example.ecommerce.product.controller.admin.spu.vo;

import com.example.ecommerce.frame.common.pojo.PageParam;
import lombok.Data;

import java.util.Map;

@Data
public class PageProductSpuReqVO extends PageParam {
    private Map<String, String> condition;
    private boolean sort;
    private String sortName;
    private int sortType; //asc: 0; desc: 1

}
