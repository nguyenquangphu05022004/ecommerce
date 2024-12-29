package com.example.ecommerce.trade.controller.app.cart.vo;

import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.system.controller.admin.user.vo.SellerResVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertSet;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertToMapSet;

@Data
public class CartListRespVO {
    private Map<SellerResVO, Set<CartItemRespVO>> sellerMapItem;

    public CartListRespVO(List<Cart> carts) {
        this.sellerMapItem = convertToMapSet(convertSet(carts, cart -> {
            return new Pair<>(
                    new SellerResVO(cart.getProductSku().getProductSpu().getSeller()),
                    new CartItemRespVO(cart)
            );
        }));
    }

}
