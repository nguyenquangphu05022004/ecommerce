package com.example.ecommerce.production.dal.dataobject.properties;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "production_product_properties")
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
public class ProductProperty extends BaseEntity {
    private String name;
}
