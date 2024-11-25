package com.example.ecommerce.production.dal.dataobject.brand;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "production_brands")
@NoArgsConstructor
public class ProductBrand extends BaseEntity {
    private String name;
    private String slug;
    private String avatar;
}
