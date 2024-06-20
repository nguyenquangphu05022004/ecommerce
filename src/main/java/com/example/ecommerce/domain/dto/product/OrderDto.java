package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.domain.dto.ENUM.Payment;
import com.example.ecommerce.domain.dto.ENUM.Size;
import com.example.ecommerce.domain.dto.ENUM.Status;
import com.example.ecommerce.domain.dto.user.UserResponseInfo;
import com.example.ecommerce.utils.SystemUtils;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class OrderDto extends BaseDto {
    private Size size;
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
