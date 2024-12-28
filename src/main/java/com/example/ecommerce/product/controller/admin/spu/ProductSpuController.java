package com.example.ecommerce.product.controller.admin.spu;


import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.operatelog.annotation.OperationLog;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductDetailsRespVO;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuCreateReqVO;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuResVO;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuUpdateBaseReqVO;
import com.example.ecommerce.product.service.spu.ProductSpuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/product/spus")
@Tag(name = "Product SPU")
@CrossOrigin("*")
public class ProductSpuController {
    private final ProductSpuService productSpuService;


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

    @GetMapping("/seller/{userMemberId}")
    public CommonResult<PageResult<ProductSpuResVO>> getListProductBySellerUserMemberId(
            @PathVariable("userMemberId") Long userMemberId,
            @RequestParam(value = "page", defaultValue = "1") int page) {
        return CommonResult.success(productSpuService.getPageProductSpuByUserId(userMemberId, page), ProductSpuResVO::new);
    }

    @GetMapping("/{productSpuId}")
    @PermitAll
    @Operation(summary = "Get product spu by id")
    public CommonResult<ProductDetailsRespVO> getProductSpuByProductSpuId(@PathVariable("productSpuId") Long productSpuId) {
        return CommonResult.success(productSpuService.getDetailsProduct(productSpuId));
    }

}
