package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "bills")
@NoArgsConstructor
@Data
@Getter
@SuperBuilder(toBuilder = true)

public class Bill extends Base{

    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    //auto insert after order success


}
