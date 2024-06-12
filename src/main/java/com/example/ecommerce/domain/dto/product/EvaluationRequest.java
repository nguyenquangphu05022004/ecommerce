package com.example.ecommerce.domain.dto.product;

import com.example.ecommerce.domain.dto.user.UserResponseInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EvaluationRequest {
    private Long productId;
    private Integer rating;
    private String content;
    private Integer numberOfLike;
    private List<ImageDto> images =new ArrayList<>();
}
