package com.example.ecommerce.dto;

import com.example.ecommerce.domain.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@SuperBuilder(toBuilder = true)

public class BillDto extends BaseDto{
    private String name;
    private Status status;
    private OrderDto order;
    private String urlUpdateStatus;
}
