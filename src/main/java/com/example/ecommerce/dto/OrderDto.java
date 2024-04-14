package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.utils.SystemUtils;
import jakarta.persistence.Transient;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder(toBuilder = true)
public class OrderDto extends BaseDto{

    private UserDto user;
    private ProductDto product;
    private Integer quantity;
    private Payment payment;
    private String status;
    private Integer percent;
    private Long couponId;

    public String getFormatTotalPrice() {
        Integer totalPrice = 0;
        if(quantity != null && product != null && product.getPrice() != null) {
            totalPrice =  quantity * product.getPrice() + product.getVendor().getPerMoneyDelivery();
            Integer x = totalPrice/100;
            totalPrice = totalPrice - x*percent;
        }
        return SystemUtils.getFormatNumber(totalPrice);
    }
}
