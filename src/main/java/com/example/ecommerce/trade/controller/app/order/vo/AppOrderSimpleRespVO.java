package com.example.ecommerce.trade.controller.app.order.vo;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import lombok.Data;

import static com.example.ecommerce.frame.common.string.StringUtils.convertToString;

@Data
public class AppOrderSimpleRespVO {
    private Long id;
    private Integer totalPrice;
    private Integer totalProduct;
    private String orderStatus;
    private String createdDate;
    private Boolean combinationShop;
    private String products;
    private String addressDetails;
    private String paymentMode;
    private String paymentStatus;
    public AppOrderSimpleRespVO(Order order) {
        this.id = order.getId();
        this.paymentStatus = order.getPaymentStatus().name();
        this.paymentMode = order.getPaymentMode().getType();
        this.addressDetails = order.getAddressDetails();
        this.totalPrice = order.totalPrice();
        this.totalProduct = order.totalProduct();
        this.orderStatus = order.getOrderStatus().getValue();
        this.createdDate = DateTimeUtils.format(order.getCreatedDate());
        this.combinationShop = order.getCombinationOfSellers();
        this.products = convertToString(order.getLineItems(), lineItem -> {
            String productNames = convertToString(lineItem.getItems(), item -> item.getProductSku().getProductSpu().getName(), ", ");
            return lineItem.getSeller().getShopName() + ": " + productNames;
        }, "\n");
    }
}
