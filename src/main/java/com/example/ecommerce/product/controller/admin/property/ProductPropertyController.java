package com.example.ecommerce.product.controller.admin.property;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.service.property.ProductPropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.pojo.CommonResult.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin-api/product/properties")
@Tag(name = "Product Property")
@CrossOrigin("*")
public class ProductPropertyController {
    private final ProductPropertyService productPropertyService;

    @PostMapping
    @PreAuthorize("@ss.hasPermission('production:product-property:create')")
    public CommonResult<ProductPropertyVO> createProperty(@RequestBody ProductPropertyVO req) {
        return success(this.productPropertyService.createProductProperty(req), ProductPropertyVO::new);
    }
    @PutMapping
    @PreAuthorize("@ss.hasPermission('production:product-property:update')")
    public CommonResult<ProductPropertyVO> updateProperty(@RequestBody ProductPropertyVO req) {
        return success(this.productPropertyService.updateProductProperty(req), ProductPropertyVO::new);
    }

    @GetMapping
    @Operation(summary = "Get all product properties that were created by current user")
    public CommonResult<List<ProductPropertyVO>> getListProductProperty() {
        return success(convertList(productPropertyService.getListProductProperty(), ProductPropertyVO::new));
//        return success(convertList(productPropertyService.getListProductPropertyByOwner(getLoginUserMemberId()), ProductPropertyVO::new));
    }

}
