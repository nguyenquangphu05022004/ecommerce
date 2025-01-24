package com.example.ecommerce.product.dal.dataobject.spu;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "production_spu_info")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ProductSpuInfo extends BaseEntity {
    private String value;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private ProductProperty property;
}
