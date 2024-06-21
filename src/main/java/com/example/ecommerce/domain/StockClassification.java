package com.example.ecommerce.domain;

import com.example.ecommerce.domain.dto.ENUM.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "stock_classifications")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class StockClassification extends Base{
    private Integer quantityOfProduct;
    @Enumerated(EnumType.STRING)
    private Size size;
    private int seller;
}
