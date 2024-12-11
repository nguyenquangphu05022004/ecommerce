package com.example.ecommerce.trade.controller.order.vo;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.product.controller.sku.vo.ProductSkuResVO;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.system.controller.user.vo.AddressResVO;
import com.example.ecommerce.system.controller.user.vo.SellerResVO;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.trade.dal.dataobject.cart.Cart;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderCheckoutResVO {
    private AddressResVO address;
    private Set<LineItem> lineItems;


    @Getter
    public static class LineItem {
        private SellerResVO seller;
        private Set<Item> items;

        public LineItem(Seller seller, Set<Cart> items) {
            this.seller = new SellerResVO(seller);
            this.items = CollUtils.convertSet(items, Item::new);
        }

        @Getter
        public static class Item {
            private ProductSkuResVO productSku;
            private Integer quantity;

            public Item(Cart cart) {
                this.productSku = new ProductSkuResVO(cart.getProductSku());
                this.quantity = cart.getQuantity();
            }
            public Integer totalPrice() {
                return quantity * productSku.getPrice();
            }
        }

        public Integer totalProduct() {
            return items.size();
        }
        public Integer totalPrice() {
            return items.stream().mapToInt(s -> s.totalPrice()).sum();
        }
    }
}
