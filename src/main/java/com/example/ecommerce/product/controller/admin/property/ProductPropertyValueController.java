package com.example.ecommerce.product.controller.admin.property;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueReqVO;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueResVO;
import com.example.ecommerce.product.service.property.ProductPropertyValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin-api/product/property-values")
@Tag(name = "Product Property Value")
@CrossOrigin("*")
public class ProductPropertyValueController {
    private final ProductPropertyValueService productPropertyValueService;

    @PostMapping
    @Operation(summary = "Assign value for property")
    @PreAuthorize("@ss.hasPermission('production:product-property-value:create')")
    public CommonResult<ProductPropertyValueResVO> createProductPropertyValue(@RequestBody ProductPropertyValueReqVO req) {
        return success(productPropertyValueService.createProductPropertyValue(req), ProductPropertyValueResVO::new);
    }

    @PutMapping
    @PreAuthorize("@ss.hasPermission('production:product-property-value:update')")
    @Operation(summary = "Update value for property")
    public CommonResult<ProductPropertyValueResVO> updateProductPropertyValue(@RequestBody ProductPropertyValueReqVO reqVO) {
        return success(productPropertyValueService.updateProductPropertyValue(reqVO), ProductPropertyValueResVO::new);
    }

    @Operation(summary = "Get all values of property")
    @GetMapping("/property/{propertyId}")
    public CommonResult<List<ProductPropertyValueResVO>> getListProductPropertyValueByPropertyId(@PathVariable("propertyId") Long propertyId) {
        return success(convertList(productPropertyValueService.getListProductPropertyValueByPropertyId(propertyId), ProductPropertyValueResVO::new));
    }
}
