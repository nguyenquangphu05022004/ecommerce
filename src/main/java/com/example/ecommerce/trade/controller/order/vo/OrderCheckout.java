package com.example.ecommerce.trade.controller.order.vo;

import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.promotion.controller.coupon.CouponRespVO;
import com.example.ecommerce.system.controller.user.vo.AddressResVO;
import com.example.ecommerce.system.controller.user.vo.SellerResVO;
import com.example.ecommerce.trade.controller.cart.vo.CartItemRespVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderCheckout {
    private Map<SellerResVO, Pair<Set<CartItemRespVO>, CouponRespVO>> itemCheckout;
}
