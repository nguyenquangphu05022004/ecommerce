package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@Table(name = "basket")
public class Basket extends Base{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;
    private Integer quantity;

    @Transient
    public Integer getTotalPrice() {
        if(quantity != null && productType != null && productType.getStock().getPrice() != null) {
            return quantity * productType.getStock().getPrice();
        }
        return 0;
    }
}
