package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@Table(name = "basket")
public class Basket extends Base{

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
    private Integer quantity;

    @Transient
    public Integer getTotalPrice() {
        if(quantity != null && stock != null && stock.getPrice() != null) {
            return quantity * stock.getPrice();
        }
        return 0;
    }
}
