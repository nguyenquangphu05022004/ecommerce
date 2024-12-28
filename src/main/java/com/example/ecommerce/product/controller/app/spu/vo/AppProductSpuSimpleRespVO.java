package com.example.ecommerce.product.controller.app.spu.vo;

import com.example.ecommerce.promotion.controller.admin.discount.vo.self.DiscountRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.Description;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class AppProductSpuSimpleRespVO {
    private Long id;
    private Integer maxPrice;
    private Integer minPrice;

    @Schema(description = "Anh ve san pham")
    private String imageUrl;

    @Schema(description = "San pham co dang giam gia hay khong")
    private DiscountRespVO discount;

    @Schema(description = "So luong san pham da ban")
    private Integer sold;
}
