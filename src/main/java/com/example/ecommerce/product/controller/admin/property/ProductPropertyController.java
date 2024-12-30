package com.example.ecommerce.product.controller.admin.property;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
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
@Tag(name = "Property")
public class ProductPropertyController {
    private final ProductPropertyService productPropertyService;

    @PostMapping
    @Operation(summary = "Tao property")
    @PreAuthorize("@ss.hasPermission('product-property:update')")
    public CommonResult<ProductPropertyVO> createProperty(@RequestBody ProductPropertyVO req) {
        return success(this.productPropertyService.createProductProperty(req), ProductPropertyVO::new);
    }

    @PutMapping
    @Operation(summary = "Cap nhat property")
    @PreAuthorize("@ss.hasPermission('product-property:update')")
    public CommonResult<ProductPropertyVO> updateProperty(@RequestBody ProductPropertyVO req) {
        return success(this.productPropertyService.updateProductProperty(req), ProductPropertyVO::new);
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('product-property:get')")
    @Operation(summary = "Lay danh sach property, chia trang")
    public CommonResult<PageResult<ProductPropertyVO>> getListProductProperty(@RequestBody PageParam req) {
        PageResult<ProductProperty> pageResult = this.productPropertyService.getPageProperty(req);
        return success(pageResult, ProductPropertyVO::new, "");
    }

    @GetMapping("/my/page")
    @PreAuthorize("@ss.hasPermission('product-property:get')")
    @Operation(summary = "Lay danh sach property theo nguoi tao ra no(user hien tai), chia trang")
    public CommonResult<PageResult<ProductPropertyVO>> getMyPageProperty(@RequestBody PageParam req) {
        PageResult<ProductProperty> pageResult = this.productPropertyService.getPagePropertyByOwnerId(SecurityUtils.getLoginUserMemberId(), req);
        return success(pageResult, ProductPropertyVO::new, "");
    }

}
