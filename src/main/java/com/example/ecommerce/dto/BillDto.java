package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Status;
import lombok.*;

@Data
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class BillDto extends BaseDto{
    private String name;
    private Status status;
    private OrderDto order;
}
