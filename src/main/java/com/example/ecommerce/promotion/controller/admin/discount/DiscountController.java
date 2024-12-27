package com.example.ecommerce.promotion.controller.admin.discount;


import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.promotion.controller.admin.discount.vo.self.DiscountCreateReqVO;
import com.example.ecommerce.promotion.controller.admin.discount.vo.self.DiscountRespVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;
import com.example.ecommerce.promotion.service.discount.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/promotion/discounts")
@Tag(name = "Discount")
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping
    @PreAuthorize("@ss.hasPermission('promotion-discount:update'")
    @Operation(summary = "Tao giam gia san pham")
    public CommonResult<DiscountRespVO> createDiscountForProduct(@RequestBody DiscountCreateReqVO req) {
        Discount discount = this.discountService.createDiscount(req);
        return CommonResult.success(discount, DiscountRespVO::new);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@ss.hasPermission('promotion-discount:delete'")
    @Operation(summary = "Xoa giam gia san pham")
    public CommonResult<Boolean> deleteDiscountProduct(@PathVariable("id") Long id) {
        this.discountService.deleteById(id);
        return CommonResult.success(true);
    }
}
