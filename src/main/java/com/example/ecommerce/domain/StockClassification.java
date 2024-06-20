package com.example.ecommerce.domain;

import com.example.ecommerce.domain.dto.ENUM.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "stock_classifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockClassification extends Base{
    private Integer quantityOfProduct;
    @Enumerated(EnumType.STRING)
    private Size size;
}
