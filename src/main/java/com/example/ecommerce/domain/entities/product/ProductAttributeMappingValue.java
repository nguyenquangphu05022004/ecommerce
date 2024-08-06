package com.example.ecommerce.domain.entities.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "products_attribute_mapping_value")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ProductAttributeMappingValue extends BaseEntity  {
    private String value;
    @ManyToOne
    @JoinColumn(name = "product_attribute_id")
    private ProductAttribute productAttribute;
}
