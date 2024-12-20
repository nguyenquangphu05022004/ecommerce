package com.example.ecommerce.trade.controller.order.vo;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import lombok.Data;

import static com.example.ecommerce.frame.common.string.StringUtils.convertToString;

@Data
public class OrderSimpleRespVO {
    private Long id;
    private Integer totalPrice;
    private Integer totalProduct;
    private String orderStatus;
    private String createdDate;
    private boolean combinationShop;
    private String products;
    private String addressDetails;
    private String paymentMode;
    public OrderSimpleRespVO(Order order) {
        this.id = order.getId();
        this.paymentMode = order.getPaymentMode().getType();
        this.addressDetails = order.getAddressDetails();
        this.totalPrice = order.totalPrice();
        this.totalProduct = order.totalProduct();
        this.orderStatus = order.getOrderStatus().getValue();
        this.createdDate = DateTimeUtils.format(order.getCreatedDate());
        this.combinationShop = CollUtils.size(order.getLineItems()) > 1;
        this.products = convertToString(order.getLineItems(), lineItem -> {
            String productNames = convertToString(lineItem.getItems(), item -> item.getProductSku().getProductSpu().getName(), ", ");
            return lineItem.getSeller().getShopName() + ": " + productNames;
        }, "\n");
    }
}
