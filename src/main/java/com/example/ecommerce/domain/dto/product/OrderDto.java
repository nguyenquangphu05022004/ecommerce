package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.domain.dto.ENUM.Payment;
import com.example.ecommerce.domain.dto.user.UserResponseInfo;
import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder(toBuilder = true)
public class OrderDto extends BaseDto {

    private UserResponseInfo user;
    private StockResponse stock;
    private Integer quantity;
    private Payment payment;
    private int couponPercent;
    private boolean approval;
    private boolean purchased;
    private boolean shipStatus;


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
