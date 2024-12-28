package com.example.ecommerce.product.controller.app.spu;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.admin.spu.vo.PageProductSpuReqVO;
import com.example.ecommerce.product.controller.app.spu.vo.AppProductSpuDetailsRespVO;
import com.example.ecommerce.product.controller.app.spu.vo.AppProductSpuSimpleRespVO;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.service.spu.ProductSpuService;
import com.example.ecommerce.promotion.service.discount.DiscountService;
import com.example.ecommerce.statistic.dal.dataobject.product.ProductStatistic;
import com.example.ecommerce.statistic.enums.OperationType;
import com.example.ecommerce.statistic.service.ProductStatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/product/spus")
@Tag(name = "App_Product_Spu")
@RequiredArgsConstructor
public class AppProductSpuController {
    private final ProductSpuService productSpuService;
    private final ProductStatisticService productStatisticService;
    private final DiscountService discountService;

    @Operation(summary = "Lay danh sach product spu, chia trang")
    @PermitAll
    @GetMapping
    public CommonResult<PageResult<AppProductSpuSimpleRespVO>> getPageProductSpu(
            @RequestBody PageProductSpuReqVO req
    ) {
        PageResult<ProductSpu> pageResult = productSpuService.getPageProductSpu(req);
        return CommonResult.success(pageResult, spu -> {
            AppProductSpuSimpleRespVO appSimple = AppProductSpuSimpleRespVO.builder()
                    .id(spu.getId()).minPrice(spu.getMinPrice()).maxPrice(spu.getMaxPrice())
                    .imageUrl(CollUtils.getFirst(spu.getProductSkus(), sku -> sku.getImage()))
                    .sold(productStatisticService.getProductStatistic(spu.getId()).getSold())
                    .discount(null)
                    .build();

            return appSimple;
        }, "");
    }

    @Operation(summary = "Xem chi tiet product spu")
    @PermitAll
    @GetMapping("/{id}")
    public CommonResult<AppProductSpuDetailsRespVO> getDetailProductSpu(@PathVariable("id") Long id) {
        ProductSpu productSpu = productSpuService.getProductSpuById(id);
        ProductStatistic productStatistic = this.productStatisticService.getProductStatistic(id);
        this.productStatisticService.doUpdateProductStatistic(id, OperationType.ADD, "browseCount");
        return null;
    }

}
