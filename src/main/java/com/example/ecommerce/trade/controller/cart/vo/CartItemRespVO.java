package com.example.ecommerce.trade.controller.cart.vo;

import com.example.ecommerce.product.controller.sku.vo.ProductSkuTradeResVO;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import lombok.Data;

@Data
public class CartItemRespVO {
    private Long id;
    private Boolean selected;
    private ProductSkuTradeResVO product;
    private Integer quantity;
    private Integer totalPrice;
    public CartItemRespVO(Cart cart) {
        this.id = cart.getId();
        this.selected = cart.getSelected();
        this.product = new ProductSkuTradeResVO(cart.getProductSku());
        this.quantity = cart.getQuantity();
        this.totalPrice = cart.totalPrice();
    }
}
