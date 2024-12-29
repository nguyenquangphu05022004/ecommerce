package com.example.ecommerce.trade.controller.app.order;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.trade.controller.app.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.controller.app.order.vo.OrderDetailsRespVO;
import com.example.ecommerce.trade.controller.app.order.vo.OrderSimpleRespVO;
import com.example.ecommerce.trade.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app-api/trade/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void createOrder(OrderDetailsReqVO req) {
        this.orderService.createOrder(req);
    }
    @GetMapping("/my-orders")
    public CommonResult<List<OrderSimpleRespVO>> getMyListOrder() {
        return success(CollUtils.convertList(this.orderService.getAllListOrder(SecurityUtils.getLoginUserMemberId()), OrderSimpleRespVO::new));
    }

    @GetMapping("/{id}")
    public CommonResult<OrderDetailsRespVO> getOrderDetails(@PathVariable("id") Long id) {
        return success(new OrderDetailsRespVO(orderService.getOrderByUserIdAndOrderId(
                 SecurityUtils.getLoginUserMemberId(), id
        )));
    }
    @PutMapping("/cancel/{id}")
    public CommonResult<Boolean> cancelOrder(@PathVariable("id") Long orderId) {
        orderService.cancelOrder(SecurityUtils.getLoginUserMemberId(), orderId);
        return success(true);
    }
}
