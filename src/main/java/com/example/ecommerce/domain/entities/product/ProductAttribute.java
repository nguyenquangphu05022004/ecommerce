package com.example.ecommerce.domain.entities.product;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.SlugLink;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "products_attribute")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ProductAttribute extends BaseEntity implements SlugLink {
    private String name;
}
