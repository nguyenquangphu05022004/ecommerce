package com.example.ecommerce.product.controller.app.spu.vo;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.object.ObjectUtils;
import com.example.ecommerce.product.controller.admin.brand.vo.ProductBrandResVO;
import com.example.ecommerce.product.controller.admin.category.vo.ProductCategoryResVO;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.promotion.controller.admin.discount.vo.self.DiscountRespVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;
import com.example.ecommerce.statistic.dal.dataobject.product.ProductStatistic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.ecommerce.frame.common.object.ObjectUtils.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AppProductSpuSimpleRespVO {
    private Long id;
    private String name;
    private Integer maxPrice;
    private Integer minPrice;
    private String sendFrom;
    @Schema(description = "The loai")
    private ProductCategoryResVO category;
    @Schema(description = "Thuong hieu")
    private ProductBrandResVO brand;

    @Schema(description = "Anh ve san pham")
    private String imageUrl;

    @Schema(description = "San pham co dang giam gia hay khong")
    private DiscountRespVO discount;

    @Schema(description = "So luong san pham da ban")
    private Integer sold;


    public AppProductSpuSimpleRespVO(ProductSpu spu, Discount discount, ProductStatistic statistic) {
        this.id = spu.getId();
        this.name = spu.getName();
        this.maxPrice = spu.getMaxPrice();
        this.minPrice = spu.getMinPrice();
        this.category = get(spu.getProductCategory(), ProductCategoryResVO::new);
        this.brand = get(spu.getProductBrand(), ProductBrandResVO::new);
        this.imageUrl = CollUtils.getFirst(spu.getProductSkus(), ProductSku::getImage);
        this.discount = get(discount, DiscountRespVO::new);
        this.sold = get(statistic, ProductStatistic::getSold);
        this.sendFrom = spu.getSendFrom();
    }
}
