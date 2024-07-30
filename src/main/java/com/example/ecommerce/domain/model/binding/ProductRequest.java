package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.domain.entities.product.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ProductRequest {
    @NotNull
    private Product.Language language;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long brandId;
    @NotEmpty
    @Length(min = 50)
    private String description;
    @NotNull
    private Integer price;
    private boolean combination;
}
