package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bills")
@NoArgsConstructor
@Data
@Getter
public class Bill extends Base{

    private String name;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Enumerated
    private Status status;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
