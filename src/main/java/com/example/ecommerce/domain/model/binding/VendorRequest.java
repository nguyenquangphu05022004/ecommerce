package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.common.InvalidMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class VendorRequest extends RegisterRequest{
    @NotEmpty(message = InvalidMessage.NOT_EMPTY)
    @Length(min = 6)
    private String shopName;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Integer perMoneyDelivery;
}
