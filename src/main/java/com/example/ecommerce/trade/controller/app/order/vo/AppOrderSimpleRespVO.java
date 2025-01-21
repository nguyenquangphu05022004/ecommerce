package com.example.ecommerce.trade.controller.app.order.vo;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.frame.common.object.ObjectUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.enums.PaymentMode;
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
    private PaymentMode paymentMode;
    private Pair<String,String> paymentStatus;
    public AppOrderSimpleRespVO(Order order) {
        this.id = order.getId();
        this.paymentStatus = new Pair<>(order.getPaymentStatus().name(), order.getPaymentStatus().getValue());
        this.paymentMode = order.getPaymentMode();
        this.addressDetails = order.getAddressDetails();
        this.totalPrice = order.totalPrice();
        this.totalProduct = order.totalProduct();
        this.orderStatus = order.getOrderStatus().getValue();
        this.createdDate = DateTimeUtils.format(order.getCreatedDate());
        this.combinationShop = order.getCombinationOfSellers() != null;
        this.products = convertToString(order.getLineItems(), lineItem -> {
            String productNames = convertToString(lineItem.getItems(), item -> {
                String properties = convertToString(item.getProductSku().getProductSkuProperties(),
                        property -> property.getProductPropertyValue().getPropertyValue(), "-");
                return item.getProductSku().getProductSpu().getName() + "(" + properties + ")";
            }, ", ");
            return "-<b style='color:red'>" + lineItem.getSeller().getShopName() + "</b>: " + productNames;
        }, "<br/>");
    }
}
