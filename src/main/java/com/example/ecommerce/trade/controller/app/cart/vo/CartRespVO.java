package com.example.ecommerce.trade.controller.app.cart.vo;

import com.example.ecommerce.system.controller.admin.user.vo.SellerResVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class CartRespVO {
    private SellerResVO seller;
    private Set<CartItemRespVO> cartItems;
}
