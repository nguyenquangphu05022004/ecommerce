package com.example.ecommerce.product.controller.spu.self;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.operatelog.annotation.OperationLog;
import com.example.ecommerce.product.controller.spu.detail.vo.ProductSpuDetailReqVO;
import com.example.ecommerce.product.controller.spu.detail.vo.ProductSpuDetailResVO;
import com.example.ecommerce.product.service.spu.ProductSpuDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-spu-details")
@Tag(name = "Product SPU DETAILS")
@CrossOrigin("*")
public class ProductSpuDetailController {
    private final ProductSpuDetailService productSpuDetailService;
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    @PreAuthorize("@ss.hasPermission('production:product-spu-detail:create/update')")
    @Operation(summary = "Add/Update detail Product SPU")
    @OperationLog
    public CommonResult<Boolean> addDetail(@RequestBody ProductSpuDetailReqVO reqVO) {
        success(productSpuDetailService.addProductDetail(reqVO), ProductSpuDetailResVO::new);
        return success(true);
    }

    @GetMapping("/product-spu/{spuId}")
    public CommonResult<List<ProductSpuDetailResVO>> getListSpuDetailBySpuId(@PathVariable("spuId") Long spuId) {
        return success(CollUtils.convertList(productSpuDetailService.getListBySpuId(spuId), ProductSpuDetailResVO::new));
    }

    @DeleteMapping("/{productSpuDetailId}")
    @Operation(summary = "delete details")
    @OperationLog
    public CommonResult<Boolean> deleteDetail(@PathVariable("productSpuDetailId") Long productSpuDetailId) {
        this.productSpuDetailService.delete(productSpuDetailId);
        return success(true);
    }

}
