package com.example.ecommerce.trade.controller.app.order.vo;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.product.controller.admin.sku.vo.ProductSkuSimpleRespVO;
import com.example.ecommerce.promotion.controller.admin.coupon.vo.CouponRespVO;
import com.example.ecommerce.system.controller.admin.user.vo.SellerResVO;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.dataobject.order.OrderItem;
import com.example.ecommerce.trade.dal.dataobject.order.OrderLineItem;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class AppOrderDetailsRespVO extends AppOrderSimpleRespVO {
    private List<OrderLineItemRespVO> lineItems;
    public AppOrderDetailsRespVO(Order order) {
        super(order);
        this.lineItems = CollUtils.convertList(order.getLineItems(), OrderLineItemRespVO::new);
    }
    @Data
    public static class OrderLineItemRespVO {
        private SellerResVO seller;
        private CouponRespVO coupon;
        private List<OrderItemRespVO> items;
        private Boolean commentStatus;
        public OrderLineItemRespVO(OrderLineItem orderLineItem) {
            this.seller = new SellerResVO(orderLineItem.getSeller());
            this.coupon = orderLineItem.getCoupon() != null ? new CouponRespVO(orderLineItem.getCoupon()) : null;
            this.items = CollUtils.convertList(orderLineItem.getItems(), item -> new OrderItemRespVO(item));
            this.commentStatus = orderLineItem.getCommentStatus();
        }

    }

    @Data
    public static class OrderItemRespVO {
        private ProductSkuSimpleRespVO product;
        private Integer quantity;

        public OrderItemRespVO(OrderItem orderItem) {
            this.product = new ProductSkuSimpleRespVO(orderItem.getProductSku());
            this.quantity = orderItem.getQuantity();
        }
    }
}
