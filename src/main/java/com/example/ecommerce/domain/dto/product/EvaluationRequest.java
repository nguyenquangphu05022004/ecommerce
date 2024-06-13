package com.example.ecommerce.domain.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EvaluationRequest {
    private Long productId;
    private Integer rating;
    private String content;
}
