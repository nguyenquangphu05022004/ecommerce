package com.example.ecommerce.trade.controller.cart.vo;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.collection.StreamUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import lombok.Getter;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CartResVO {
    private Boolean selected;
    private Integer quantity;
    private Long id;
    private Integer totalPrice;

    private Product product;

    public CartResVO(Cart cart) {
        this.selected = cart.getSelected();
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.totalPrice = cart.totalPrice();
        this.product = new Product(cart.getProductSku());
    }

    @Getter
    private static class Product {
        private Long productSpuId;
        private Long productSkuId;
        private String name;
        private String image;
        private Integer price;
        private Map<ProductProperty, Set<Pair<ProductPropertyValue, Boolean>>> properties;

        public Product(ProductSku sku) {
            this.productSpuId = sku.getProductSpu().getId();
            this.productSkuId = sku.getId();
            this.name = sku.getProductSpu().getName();
            this.image = sku.getImage();
            this.price = sku.getPrice();
            this.properties = mapProperties(sku);
        }

        private Map<ProductProperty, Set<Pair<ProductPropertyValue, Boolean>>> mapProperties(ProductSku sku) {
            Set<ProductSku> productSkus = sku.getProductSpu().getProductSkus();

            Set<Map<ProductProperty, Set<Pair<ProductPropertyValue, Boolean>>>> collect = productSkus.stream().map(s -> {
                return MapUtils.convertToMapSet(CollUtils.convertSet(s.getProductSkuProperties(), ss -> {
                    return new Pair<>(
                            ss.getProductProperty(),
                            new Pair<>(ss.getProductPropertyValue(), StreamUtils.filter(sku.getProductSkuProperties(), p -> {
                                return p.getProductPropertyValue().equals(ss.getProductPropertyValue());
                            }))
                    );
                }));
            }).collect(Collectors.toSet());

            return MapUtils.convertListMap(collect);

        }
    }
}
