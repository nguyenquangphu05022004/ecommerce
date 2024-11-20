package com.example.ecommerce.domain.entities;

import com.example.ecommerce.domain.notification.Notification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
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

    private State state;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Notification> notifications;


    @Transient
    public Integer getTotalPrice() {
        return lineItems.stream().mapToInt(lineItem -> {
            return lineItem.getItems().stream().mapToInt(item ->
                    item.getProductInventory().getPrice()* item.getQuantity())
                    .sum();
        }).sum();
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }

}
