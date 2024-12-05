package com.example.ecommerce.product.controller.category;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.operatelog.annotation.OperationLog;
import com.example.ecommerce.product.controller.category.vo.ProductCategoryCreateReqVO;
import com.example.ecommerce.product.controller.category.vo.ProductCategoryResVO;
import com.example.ecommerce.product.controller.category.vo.ProductCategoryUpdateReqVO;
import com.example.ecommerce.product.service.category.ProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Tag(name = "Production - Category")
@CrossOrigin("*")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping
    @Operation(summary = "Create product category")
    @OperationLog
    public CommonResult<ProductCategoryResVO> createProductCategory(@RequestBody ProductCategoryCreateReqVO reqVO) {
        return success(productCategoryService.createProductCategory(reqVO), ProductCategoryResVO::new);
    }

    @PutMapping
    @Operation(summary = "Update product category")
    @OperationLog
    public CommonResult<ProductCategoryResVO> updateProductCategory(@RequestBody ProductCategoryUpdateReqVO reqVO) {
        return success(productCategoryService.updateProductCategory(reqVO), ProductCategoryResVO::new);
    }
    @GetMapping
    @Operation(summary = "Get list product category")
    public CommonResult<List<ProductCategoryResVO>> getListProductCategory() {
        return success(convertList(productCategoryService.getListProductCategory(), ProductCategoryResVO::new));
    }

    @PutMapping("/uploads")
    @Operation(summary = "Update thumbnail for product category")
    @OperationLog
    public CommonResult<ProductCategoryResVO> updateProductCategoryThumbnail(
            @PathVariable("productCategoryId") Long productCategoryId,
            @RequestParam("fileImage")MultipartFile fileImage) {
        return success(this.productCategoryService.updateProductCategoryThumbnail(productCategoryId, fileImage), ProductCategoryResVO::new);
    }
}
