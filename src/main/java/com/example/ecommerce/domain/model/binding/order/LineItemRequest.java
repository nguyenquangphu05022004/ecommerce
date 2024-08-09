package com.example.ecommerce.domain.model.binding.order;

import com.example.ecommerce.common.InvalidMessage;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@Builder
@AllArgsConstructor
public class LineItemRequest {
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Long vendorId;
    private Long couponId;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private List<ItemRequest> items;
}
