package com.example.ecommerce.trade.service.cart;

import com.example.ecommerce.trade.controller.cart.vo.CartCreateReqVO;
import com.example.ecommerce.trade.controller.cart.vo.CartListRespVO;
import com.example.ecommerce.trade.controller.cart.vo.CartUpdateQuantityReqVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;

import java.util.List;

public interface CartService {
    void createCartProduct(Long userId, CartCreateReqVO reqVO);
    CartListRespVO getList(Long userId);
    Cart updateQuantity(CartUpdateQuantityReqVO reqVO);
    void delete(Long id);
}
