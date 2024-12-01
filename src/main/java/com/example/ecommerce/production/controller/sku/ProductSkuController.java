package com.example.ecommerce.production.controller.sku;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.production.controller.sku.vo.ProductSkuCreateReqVO;
import com.example.ecommerce.production.controller.sku.vo.ProductSkuResVO;
import com.example.ecommerce.production.controller.sku.vo.ProductSkuUpdateReqVO;
import com.example.ecommerce.production.service.sku.ProductSkuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-skus")
@Tag(name = "Product Sku")
@CrossOrigin("*")
public class ProductSkuController {
    private final ProductSkuService productSkuService;
    @PostMapping
    @Operation(summary = "Create product sku")
    @PreAuthorize("@ss.hasPermission('production:product-sku:create')")
    public CommonResult<ProductSkuResVO> createProductSku(@RequestBody ProductSkuCreateReqVO reqVO) {
        return success(productSkuService.createProductSku(reqVO), ProductSkuResVO::new);
    }

    @PutMapping
    @Operation(summary = "Update product sku")
    @PreAuthorize("@ss.hasPermission('production:product-sku:update')")
    public CommonResult<ProductSkuResVO> createProductSku(@RequestBody ProductSkuUpdateReqVO reqVO) {
        return success(productSkuService.updateProductSku(reqVO), ProductSkuResVO::new);
    }

    @GetMapping("/product-spu/{productSpuId}")
    @Operation(summary = "Get all by product spu id")
    public CommonResult<List<ProductSkuResVO>> getListByProductSpu(@PathVariable("productSpuId") Long productSpuId) {
        return success(convertList(productSkuService.getListProductSkuByProductSpuId(productSpuId), ProductSkuResVO::new));
    }

    @PutMapping("/{productSkuId}/uploads")
    public CommonResult<Boolean> updateThumbnailForProductSku(@PathVariable("productSkuId") Long productSkuId, @RequestParam("file") MultipartFile file) {
        this.productSkuService.updateImage(productSkuId, file);
        return success(true);
    }

}
