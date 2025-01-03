package com.example.ecommerce.util;

import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;

import static com.example.ecommerce.frame.test.RandomUtils.randomPojo;

public class RandomEntity {
    public static UserMember userMember(Class<? extends UserMember> x) {
        return randomPojo(x, s -> {
           s.setId(null);
        });
    }
    public static ProductSku sku(ProductSpu spu) {
        return randomPojo(ProductSku.class, s -> {
           s.setProductSkuProperties(null);  s.setProductSpu(spu); s.setId(null);
        });
    }
    public static ProductSpu spu(Seller seller) {
        return randomPojo(ProductSpu.class, s -> {
           s.setId(null); s.setSeller(seller); s.setProductBrand(null); s.setProductCategory(null);
           s.setProductSkus(null);
        });
    }
    public static Cart cart(ProductSku sku, UserMember member) {
        return randomPojo(Cart.class, c -> {
            c.setId(null); c.setProductSku(sku); c.setUserMember(member); c.setQuantity(5);
        });
    }
}
