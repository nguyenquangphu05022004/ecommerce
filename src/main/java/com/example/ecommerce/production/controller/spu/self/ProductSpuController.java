package com.example.ecommerce.production.controller.spu.self;


import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.operatelog.annotation.OperationLog;
import com.example.ecommerce.production.controller.spu.detail.vo.ProductSpuDetailReqVO;
import com.example.ecommerce.production.controller.spu.detail.vo.ProductSpuDetailResVO;
import com.example.ecommerce.production.controller.spu.self.vo.ProductSpuCreateReqVO;
import com.example.ecommerce.production.controller.spu.self.vo.ProductSpuResVO;
import com.example.ecommerce.production.controller.spu.self.vo.ProductSpuUpdateBaseReqVO;
import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.production.service.spu.ProductSpuDetailService;
import com.example.ecommerce.production.service.spu.ProductSpuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-spus")
@Tag(name = "Product SPU")
public class ProductSpuController {
    private final ProductSpuService productSpuService;
    private final ProductSpuDetailService productSpuDetailService;

    @PostMapping
    @PreAuthorize("@ss.hasPermission('production:product-spu:create')")
    @Operation(summary = "Create product spu")
    @OperationLog
    public CommonResult<ProductSpuResVO> createProductSpu(@RequestBody ProductSpuCreateReqVO reqVO) {
        return success(productSpuService.createProductSpu(reqVO), ProductSpuResVO::new);
    }

    @PutMapping
    @PreAuthorize("@ss.hasPermission('production:product-spu:update')")
    @OperationLog
    @Operation(summary = "Update product spu")
    public CommonResult<ProductSpuResVO> updateProductSpu(@RequestBody ProductSpuUpdateBaseReqVO reqVO) {
        return success(productSpuService.updateProductSpu(reqVO), ProductSpuResVO::new);
    }


    @RequestMapping(value = "/details", method = {RequestMethod.POST, RequestMethod.PUT})
    @PreAuthorize("@ss.hasPermission('production:product-spu-detail:create/update')")
    @Operation(summary = "Add/Update detail Product SPU")
    @OperationLog
    public CommonResult<ProductSpuDetailResVO> addDetail(@RequestBody ProductSpuDetailReqVO reqVO) {
        return success(productSpuDetailService.addProductDetail(reqVO), ProductSpuDetailResVO::new);
    }


    @GetMapping("/{productSpuId}")
    @PermitAll
    @Operation(summary = "Get product spu by id")
    public CommonResult<ProductSpuResVO> getProductSpuByProductSpuId(@PathVariable("productSpuId") Long productSpuId) {
        return CommonResult.success(productSpuService.getProductSpuById(productSpuId), ProductSpuResVO::new);
    }

}
