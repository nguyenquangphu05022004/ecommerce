package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Data
@Getter
@SuperBuilder(toBuilder = true)
public class Order extends Base{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    @OneToOne(mappedBy = "order")
    private Bill bill;
    @Transient
    public Integer getTotalPrice() {
        if(quantity != null && product != null && product.getPrice() != null) {
            return quantity * product.getPrice();
        }
        return 0;
    }

}
