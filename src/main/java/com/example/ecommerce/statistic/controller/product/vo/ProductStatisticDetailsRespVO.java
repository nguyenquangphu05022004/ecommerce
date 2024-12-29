package com.example.ecommerce.statistic.controller.product.vo;

import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuResVO;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuUpdateBaseReqVO;
import lombok.Data;

@Data
public class ProductStatisticDetailsRespVO extends ProductSpuUpdateBaseReqVO {

    private ProductSpuResVO productSpu;

}
