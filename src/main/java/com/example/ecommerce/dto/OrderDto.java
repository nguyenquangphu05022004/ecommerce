package com.example.ecommerce.dto;

import com.example.ecommerce.domain.Payment;
import com.example.ecommerce.domain.UserContactDetails;
import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder(toBuilder = true)
public class OrderDto extends BaseDto{

    private UserContactDetails contactDetails;
    private StockResponse stockResponse;
    private Integer quantity;
    private Payment payment;
    private int couponPercent;
    private boolean approval;
    private boolean purchased;
    private boolean shipStatus;


    public Integer getTotalPrice() {
        Integer totalPrice = 0;
        if(quantity != null && stockResponse != null && stockResponse.getPrice() != null) {
            totalPrice =  quantity * stockResponse.getPrice() +
                    stockResponse.getProductResponse()
                            .getVendorResponse()
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
