package com.example.ecommerce.trade.controller.admin.order;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.trade.controller.admin.order.vo.self.OrderSimpleRespVO;
import com.example.ecommerce.trade.controller.admin.order.vo.self.PageOrderReqVO;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin-api/trade/orders")
public class OrderController {
    private final OrderService orderService;


    @GetMapping
    @Operation(summary = "Lay toan bo don hang, chia trang")
    @PreAuthorize("@ss.hasRole('SUPER_ADMIN')")
    public CommonResult<PageResult<OrderSimpleRespVO>> getPageOrder(@RequestBody PageOrderReqVO req) {
        PageResult<Order> pageOrder = orderService.getPageOrder(req);
        return CommonResult.success(pageOrder, OrderSimpleRespVO::new);
    }

    @GetMapping("/my-orders")
    @Operation(summary = "Lay danh sach don hang, customer da dat")
    public CommonResult<PageResult<OrderSimpleRespVO>> getMyPageOrder(@RequestBody PageOrderReqVO req) {
        return null;
    }

    @PutMapping("/approval/{id}")
    @Operation(summary = "Chap thuan don hang")
    public CommonResult<Boolean> approvalOrder(@PathVariable("id") Long orderId) {
        orderService.approvalOrder(orderId);
        return CommonResult.success(true);
    }

    @PutMapping("/status")
    @Operation(summary = "Cap nhat trang thai don hang va ghi log")
    public CommonResult<Boolean> updateOrderStatus(@RequestParam("id") Long orderId,
                                                   @RequestParam("next") Boolean isNext,
                                                   @RequestParam("content") String content) {
        if(isNext) {
            orderService.updateNextStatus(orderId, content);
        } else {
            orderService.updatePreviousStatus(orderId, content);
        }
        return CommonResult.success(true);
    }

}
