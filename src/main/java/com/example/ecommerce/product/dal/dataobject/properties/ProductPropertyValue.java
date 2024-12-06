package com.example.ecommerce.product.dal.dataobject.properties;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "product_property_values")
@Entity
@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
@Setter
public class ProductPropertyValue extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_property_id")
    private ProductProperty productProperty;
    private String propertyValue;
}
