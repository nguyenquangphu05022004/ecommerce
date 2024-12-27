package com.example.ecommerce.promotion.controller.admin.discount;


import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.promotion.controller.admin.discount.vo.activity.DiscountActivityCreateReqVO;
import com.example.ecommerce.promotion.controller.admin.discount.vo.activity.DiscountActivityRespVO;
import com.example.ecommerce.promotion.controller.admin.discount.vo.activity.PageDiscountActivityReqVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.DiscountActivity;
import com.example.ecommerce.promotion.service.discount.DiscountActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.example.ecommerce.frame.common.pojo.CommonResult.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/promotion/discount/activities")
@Tag(name = "Discount Activity")
public class DiscountActivityController {
    private final DiscountActivityService discountActivityService;


    @PostMapping
    @PreAuthorize("@ss.hasPermission('promotion-discount:update'")
    @Operation(summary = "Tao hoat dong giam gia")
    public CommonResult<DiscountActivityRespVO> createDiscountActivity(@RequestBody DiscountActivityCreateReqVO req) {
        DiscountActivity discountActivity = this.discountActivityService.createDiscountActivity(req);
        return success(discountActivity, DiscountActivityRespVO::new);
    }

    @PutMapping
    @PreAuthorize("@ss.hasPermission('promotion-discount:update'")
    @Operation(summary = "Cap nhat hoat dong giam gia")
    public CommonResult<DiscountActivityRespVO> updateDiscountActivity(@RequestBody DiscountActivityCreateReqVO req) {
        DiscountActivity discountActivity = this.discountActivityService.updateDiscountActivity(req);
        return success(discountActivity, DiscountActivityRespVO::new);
    }

    @GetMapping("/page")
    @Operation(summary = "Lay danh sach hoat dong giam gia, phan chia trang")
    public CommonResult<PageResult<DiscountActivityRespVO>> getPageDiscountActivity(@RequestBody PageDiscountActivityReqVO req) {
        PageResult<DiscountActivity> pageResult = discountActivityService.getPageDiscountActivity(req);
        return success(pageResult, DiscountActivityRespVO::new, "");
    }
}
