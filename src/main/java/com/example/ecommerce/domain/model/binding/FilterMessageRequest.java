package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.domain.entities.EntityType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterMessageRequest {
    private EntityType entityType;
    private int limit = 100;
    private int page = 1;
}
