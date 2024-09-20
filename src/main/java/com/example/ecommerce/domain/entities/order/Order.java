package com.example.ecommerce.domain.entities.order;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.auth.Customer;
import com.example.ecommerce.domain.entities.order.states.OrderStateMessage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderStateMessage> orderStateMessages;
    @Transient
    public Integer getTotalPrice() {
        return lineItems.stream().mapToInt(lineItem -> {
            return lineItem.getItems().stream().mapToInt(item ->
                    item.getProductInventory().getPrice()* item.getQuantity())
                    .sum();
        }).sum();
    }

    @Transient
    public void addOrderStateMessage(OrderStateMessage orderStateMessage) {
        if(this.orderStateMessages == null) {
            this.orderStateMessages = new ArrayList<>();
        }
        this.orderStateMessages.add(orderStateMessage);
    }


}
