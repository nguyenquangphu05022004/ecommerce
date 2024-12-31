package com.example.ecommerce.trade.controller.app.order;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.trade.controller.app.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.controller.app.order.vo.AppOrderDetailsRespVO;
import com.example.ecommerce.trade.controller.app.order.vo.AppOrderSimpleRespVO;
import com.example.ecommerce.trade.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app-api/trade/orders")
public class AppOrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Dat hang")
    public void createOrder(OrderDetailsReqVO req) {
        this.orderService.createOrder(req);
    }
    @GetMapping("/my-orders")
    public CommonResult<List<AppOrderSimpleRespVO>> getMyListOrder() {
        return success(CollUtils.convertList(this.orderService.getAllListOrder(SecurityUtils.getLoginUserMemberId()), AppOrderSimpleRespVO::new));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chi tiet don hang")
    public CommonResult<AppOrderDetailsRespVO> getOrderDetails(@PathVariable("id") Long id) {
        return success(new AppOrderDetailsRespVO(orderService.getOrderByUserIdAndOrderId(
                 SecurityUtils.getLoginUserMemberId(), id
        )));
    }
    @PutMapping("/cancel/{id}")
    @Operation(summary = "Huy don hang")
    public CommonResult<Boolean> cancelOrder(@PathVariable("id") Long orderId) {
        orderService.cancelOrder(SecurityUtils.getLoginUserMemberId(), orderId);
        return success(true);
    }
}
