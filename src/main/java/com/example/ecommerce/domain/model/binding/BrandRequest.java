package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.common.InvalidMessage;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BrandRequest {
    @NotEmpty(message = InvalidMessage.NOT_EMPTY)
    private String name;
    @NotEmpty(message = InvalidMessage.NOT_EMPTY)
    private String slug;
}
