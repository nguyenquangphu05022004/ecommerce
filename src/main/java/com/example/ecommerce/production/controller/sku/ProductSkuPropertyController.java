package com.example.ecommerce.production.controller.sku;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.production.controller.sku.vo.property.ProductSkuPropertyCreateReqVO;
import com.example.ecommerce.production.controller.sku.vo.property.ProductSkuPropertyResVO;
import com.example.ecommerce.production.controller.sku.vo.property.ProductSkuPropertyUpdateReqVO;
import com.example.ecommerce.production.service.sku.ProductSkuPropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product-sku-properties")
@Tag(name = "Property about Product Sku")
public class ProductSkuPropertyController {
    private final ProductSkuPropertyService productSkuPropertyService;

    @PostMapping
    @PreAuthorize("@ss.hasPermission('production:product-sku-property:create')")
    @Operation(summary = "Create property for product sku")
    public CommonResult<ProductSkuPropertyResVO> addPropertyIntoProductSku(@RequestBody ProductSkuPropertyCreateReqVO reqVO) {
       return success(this.productSkuPropertyService.createProductSkuProperty(reqVO), ProductSkuPropertyResVO::new);
    }

    @PutMapping
    @PreAuthorize("@ss.hasPermission('production:product-sku-property:update')")
    public CommonResult<ProductSkuPropertyResVO> updatePropertyOfProductSku(@RequestBody ProductSkuPropertyUpdateReqVO reqVO) {
        return success(this.productSkuPropertyService.updateProductSkuProperty(reqVO), ProductSkuPropertyResVO::new);
    }

    @GetMapping("/product-sku/{productSkuId}")
    @Operation(summary = "Get all property of product sku")
    @PermitAll
    public CommonResult<List<ProductSkuPropertyResVO>> getListPropertyOfProductSku(@PathVariable("productSkuId") Long productSkuId) {
        return success(convertList(productSkuPropertyService.getListPropertyByProductSkuId(productSkuId), ProductSkuPropertyResVO::new));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete property from productSku")
    @PreAuthorize("@ss.hasPermission('production:product-sku-property:delete')")
    public CommonResult<Boolean> deletePropertyFromProductSku(@PathVariable("id") Long id) {
        this.productSkuPropertyService.deleteById(id);
        return success(true);
    }
}
