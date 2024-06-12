package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.ENUM.Status;
import com.example.ecommerce.domain.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@SuperBuilder(toBuilder = true)

public class BillDto extends BaseDto {
    private String name;
    private Status status;
    private OrderDto order;
    private String urlUpdateStatus;
}
