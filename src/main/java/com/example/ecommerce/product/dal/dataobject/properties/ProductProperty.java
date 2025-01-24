package com.example.ecommerce.product.dal.dataobject.properties;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "production_product_properties")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class ProductProperty extends BaseEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private ProductPropertyType productPropertyType;
    public static enum ProductPropertyType {
        SKU,
        EVALUATION,
        SPU
    }

}
