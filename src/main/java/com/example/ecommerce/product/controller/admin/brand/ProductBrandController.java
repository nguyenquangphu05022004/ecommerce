package com.example.ecommerce.product.controller.admin.brand;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.operatelog.annotation.OperationLog;
import com.example.ecommerce.product.controller.admin.brand.vo.ProductBrandCreateReqVO;
import com.example.ecommerce.product.controller.admin.brand.vo.ProductBrandResVO;
import com.example.ecommerce.product.service.brand.ProductBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin-api/product/brands")
@Tag(name = "Product Brand")
@CrossOrigin("*")
public class ProductBrandController {
    private final ProductBrandService productBrandService;

    @PostMapping
    @OperationLog
    @Operation(summary = "Create brand")
    @PreAuthorize("@ss.hasPermission('production:brand:create')")
    public CommonResult<ProductBrandResVO> createProductBrand(@RequestBody ProductBrandCreateReqVO reqVO) {
        return CommonResult.success(productBrandService.createProductBrand(reqVO), ProductBrandResVO::new);
    }

    @GetMapping
    @Operation(summary = "get List brand")
    public CommonResult<List<ProductBrandResVO>> getListProductBrand() {
        return CommonResult.success(CollUtils.convertList(productBrandService.getListProductBrand(), ProductBrandResVO::new));
    }
}
