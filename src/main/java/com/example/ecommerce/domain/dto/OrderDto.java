package com.example.ecommerce.domain.dto;

import com.example.ecommerce.domain.Payment;
import com.example.ecommerce.domain.Status;
import com.example.ecommerce.common.utils.SystemUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class OrderDto extends BaseDto {
    private StockClassificationResponse stockClassification;
    private UserResponseInfo user;
    private StockResponse stock;
    private Integer quantity;
    private Payment payment;
    private Status status;
    private int couponPercent;
    private boolean approval;
    private boolean purchased;
    private boolean received;

    public Integer getTotalPrice() {
        Integer totalPrice = 0;
        if(quantity != null && stock != null && stock.getPrice() != null) {
            totalPrice =  quantity * stock.getPrice() +
                    stock.getProduct()
                            .getVendor()
                            .getPerMoneyDelivery();
            Integer x = totalPrice/100;
            totalPrice = totalPrice - x*couponPercent;
        }
        return totalPrice;
    }

    public String getFormatTotalPrice() {
        return SystemUtils.getFormatNumber(getTotalPrice());
    }
}
