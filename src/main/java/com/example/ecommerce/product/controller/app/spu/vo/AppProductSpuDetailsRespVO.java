package com.example.ecommerce.product.controller.app.spu.vo;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;
import com.example.ecommerce.statistic.controller.product.vo.ProductStatisticSimpleRespVO;
import com.example.ecommerce.statistic.dal.dataobject.product.ProductStatistic;
import com.example.ecommerce.system.controller.admin.user.vo.SellerDetailsRespVO;
import lombok.Data;

import java.util.List;

@Data
public class AppProductSpuDetailsRespVO extends AppProductSpuSimpleRespVO{
    private List<String> sliders;
    private ProductStatisticSimpleRespVO statistic;
    private SellerDetailsRespVO seller;
    private List<AppProductSkuRespVO> skus;
    private String description;



    public AppProductSpuDetailsRespVO(ProductSpu spu, Discount discount, ProductStatistic statistic) {
        super(spu, discount, statistic);
        this.sliders = CollUtils.convertList(spu.getProductSkus(), ProductSku::getImage);
        this.statistic = new ProductStatisticSimpleRespVO(statistic);
        this.seller = new SellerDetailsRespVO();
        this.skus = CollUtils.convertList(spu.getProductSkus(), AppProductSkuRespVO::new);
        this.description = spu.getDescription();
    }

}
