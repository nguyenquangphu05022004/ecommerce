package com.example.ecommerce.service.request;

import com.example.ecommerce.service.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class OrderRequest extends OrderDto {

}