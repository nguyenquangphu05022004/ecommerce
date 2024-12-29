package com.example.ecommerce.trade.service.cart;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.trade.controller.app.cart.vo.CartCreateReqVO;
import com.example.ecommerce.trade.controller.app.cart.vo.CartListRespVO;
import com.example.ecommerce.trade.controller.app.cart.vo.CartUpdateQuantityReqVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;

import java.util.Collection;

public interface CartService {
    void createCartProduct(Long userId, CartCreateReqVO reqVO);
    CartListRespVO getList(Long userId);
    Cart updateQuantity(CartUpdateQuantityReqVO reqVO);
    Cart getCartById(Long id);
    void delete(Long id);


    default void deleteAll(Collection<Long> ids) {
        if(!CollUtils.isEmpty(ids)) {
            ids.forEach(id -> delete(id));
        }
    }
}
