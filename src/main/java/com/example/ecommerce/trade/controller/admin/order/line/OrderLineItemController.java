package com.example.ecommerce.trade.controller.admin.order.line;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.trade.controller.admin.order.line.vo.OrderLineItemRespVO;
import com.example.ecommerce.trade.controller.admin.order.line.vo.PageOrderLineItemReqVO;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLineItem;
import com.example.ecommerce.trade.service.order.OrderLineItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "Line Item")
@RequestMapping("/admin-api/trade/order/lines")
public class OrderLineItemController {

    private final OrderLineItemService orderLineItemService;

    @PutMapping("/granted")
    @Operation(summary = "Cap phep cho seller")
    @PreAuthorize("@ss.hasPermission('trade-order-line:granted')")
    public CommonResult<OrderLineItemRespVO> updateLineItemGranted(@RequestParam("id") Long lineItemId,
                                                                   @RequestParam("granted") Boolean granted) {
        OrderLineItem orderLineItem = orderLineItemService.updateItemsGranted(lineItemId, granted);
        return CommonResult.success(orderLineItem, OrderLineItemRespVO::new);
    }

    @PutMapping("/delivered")
    @Operation(summary = "Cap nhat khi items da den kho hang")
    @PreAuthorize("@ss.hasPermission('trade-order-line:delivered')")
    public CommonResult<OrderLineItemRespVO> updateLineItemAreDeliveredToWareHouse(@RequestParam("lineItemId") Long lineItemId,
                                                                 @RequestParam("checkSum") Boolean checkSum) {
        OrderLineItem orderLineItem = orderLineItemService.updateItemsAreDeliveredToWarehouse(lineItemId, checkSum);
        return CommonResult.success(orderLineItem, OrderLineItemRespVO::new);
    }

    @GetMapping("/{id}")
    @Operation(summary = "lay chi tiet")
    public CommonResult<OrderLineItemRespVO> getLineItemById(@PathVariable("id") Long id) {
        OrderLineItem orderLineItem = orderLineItemService.getOrderLineById(id);
        return CommonResult.success(orderLineItem, OrderLineItemRespVO::new);
    }

    @GetMapping("/my-order-line-items/page")
    @Operation(summary = "Lay danh sach lineItem from order cua nguoi ban")
    @PreAuthorize("@ss.hasRole('SELLER')")
    public CommonResult<PageResult<OrderLineItemRespVO>> getMyPageLineItem(@RequestBody PageOrderLineItemReqVO req) {
        return CommonResult.success(orderLineItemService.getPageOrderLineItem(req), OrderLineItemRespVO::new);
    }

}
