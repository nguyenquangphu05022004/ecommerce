package com.example.ecommerce.product.controller.admin.property;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueReqVO;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueResVO;
import com.example.ecommerce.product.service.property.ProductPropertyValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin-api/product/property/values")
@Tag(name = "Property Value")
public class ProductPropertyValueController {
    private final ProductPropertyValueService productPropertyValueService;

    @PostMapping
    @Operation(summary = "Tao value cho property")
    @PreAuthorize("@ss.hasPermission('product-property:update')")
    public CommonResult<ProductPropertyValueResVO> createProductPropertyValue(@RequestBody ProductPropertyValueReqVO req) {
        return success(productPropertyValueService.createProductPropertyValue(req), ProductPropertyValueResVO::new);
    }

    @PutMapping
    @PreAuthorize("@ss.hasPermission('product-property:update')")
    @Operation(summary = "Cap nhat value cho property")
    public CommonResult<ProductPropertyValueResVO> updateProductPropertyValue(@RequestBody ProductPropertyValueReqVO reqVO) {
        return success(productPropertyValueService.updateProductPropertyValue(reqVO), ProductPropertyValueResVO::new);
    }

    @Operation(summary = "Lay danh sach value theo property, chia trang")
    @GetMapping("/property/{propertyId}")
    public CommonResult<PageResult<ProductPropertyValueResVO>> getListProductPropertyValueByPropertyId(
            @PathVariable("propertyId") Long propertyId,
            @RequestBody PageParam req) {
        return success(productPropertyValueService.getPagePropertyValueByPropertyId(propertyId,req), ProductPropertyValueResVO::new);
    }
}
