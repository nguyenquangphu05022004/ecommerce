package com.example.ecommerce.production.dal.dataobject.properties;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "production_product_properties")
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
public class ProductProperty extends BaseEntity {
    private String name;

    public static enum ProductPropertyType {
        SPU,
        SKU,
        COMMENT
    }

}
