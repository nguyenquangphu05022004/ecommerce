package com.example.ecommerce.domain.entities.order;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.auth.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder(toBuilder = true)
public class Order extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LineItem> lineItems;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    private String stateName;
    @Transient
    public Integer getTotalPrice() {
        return lineItems.stream().mapToInt(lineItem -> {
            return lineItem.getItems().stream().mapToInt(item ->
                    item.getProductInventory().getPrice()* item.getQuantity())
                    .sum();
        }).sum();
    }


}
