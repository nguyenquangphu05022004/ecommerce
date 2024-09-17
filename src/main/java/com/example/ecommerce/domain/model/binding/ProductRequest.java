package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.common.InvalidMessage;
import com.example.ecommerce.domain.entities.product.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ProductRequest {
    @Length(min = 6)
    private String nameVn;
    @Length(min = 6)
    private String nameEn;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Long categoryId;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Long brandId;
    @NotEmpty(message = InvalidMessage.NOT_EMPTY)
    @Length(min = 10)
    private String description;
    @NotNull
    @Length(min = 6)
    private String slug;
    private boolean combination;
}
