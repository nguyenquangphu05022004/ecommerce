package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.View;

@Entity
@Data
@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@Table(name = "basket")
public class Basket extends Base{

    /*
        basket:
            id, product
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id", unique = false)
    private Product product;
    private Integer quantity;

    @Transient
    public Integer getTotalPrice() {
        if(quantity != null && product != null && product.getPrice() != null) {
            return quantity * product.getPrice();
        }
        return 0;
    }
}
