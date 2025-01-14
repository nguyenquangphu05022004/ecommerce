package com.example.ecommerce.trade.controller.app.cart.vo;

import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.system.controller.admin.user.vo.SellerResVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertSet;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertToMapSet;

@Data
@AllArgsConstructor
public class CartRespVO {
    private SellerResVO seller;
    private Set<CartItemRespVO> cartItems;
}
