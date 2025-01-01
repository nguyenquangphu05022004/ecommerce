package com.example.ecommerce.trade.controller.admin.order.log;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLog;
import com.example.ecommerce.trade.service.order.OrderLogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/trade/order/logs")
public class OrderLogController {

    private final OrderLogService orderLogService;

    @GetMapping("/order/{orderId}")
    @Operation(summary = "Lay danh sach log cua order")
    public CommonResult<List<OrderLogRespVO>> getOrderLogByOrderId(
            @PathVariable("orderId") Long orderId
    ) {
        List<OrderLog> order = orderLogService.getListByOrderId(orderId);
        return CommonResult.success(CollUtils.convertList(order, OrderLogRespVO::new));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xoa log")
    public CommonResult<Boolean> deleteOrderLog(@PathVariable("id") Long id) {
        orderLogService.deleteOrderLog(id);
        return CommonResult.success(true);
    }

}
