package com.example.ecommerce.domain.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterMessageRequest {
    private boolean isUser;
    private Long  destinationId;
}
