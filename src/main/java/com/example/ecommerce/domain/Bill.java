package com.example.ecommerce.domain;

import com.example.ecommerce.domain.dto.ENUM.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "bills")
@NoArgsConstructor
@Setter
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
