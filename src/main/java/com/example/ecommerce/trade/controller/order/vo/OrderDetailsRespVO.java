package com.example.ecommerce.trade.controller.order.vo;

import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.product.controller.sku.vo.ProductSkuResVO;
import com.example.ecommerce.system.controller.user.vo.SellerResVO;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertSet;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertToMapSet;

@Getter
public class OrderDetailsRespVO {
    private Long id;
    private Integer totalPrice;
    private Integer totalProduct;
    private boolean combinationShop;
    private Map<SellerResVO, Set<ProductSkuResVO>> sellerMapProductSku;
    private String orderStatus;

    public OrderDetailsRespVO(Order order) {
        Map<SellerResVO, Set<ProductSkuResVO>> mapItem = convertToMapSet(convertSet(order.getOrderItems(), item -> {
            return new Pair<>(new SellerResVO(item.getProductSku().getProductSpu().getSeller()), new ProductSkuResVO(item.getProductSku()));
        }));
        this.orderStatus = order.getOrderStatus().getValue();
        this.sellerMapProductSku = mapItem;
        this.combinationShop = mapItem.size() > 1;
        this.totalPrice = order.totalPrice();
        this.totalProduct = order.totalProduct();
        this.id = order.getId();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OrderDetailsRespVO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
