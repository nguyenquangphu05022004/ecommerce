package com.example.ecommerce.product.dal.dataobject.sku;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Getter
@Entity
@Table(name = "production_product_sku_properties")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ProductSkuProperty extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_sku_id")
    private ProductSku productSku;

    @ManyToOne
    @JoinColumn(name = "product_property_id")
    private ProductProperty productProperty;

    @ManyToOne
    @JoinColumn(name = "product_property_value_id")
    private ProductPropertyValue productPropertyValue;


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ProductSkuProperty that)) return false;
        if (!super.equals(object)) return false;
        return Objects.equals(productProperty, that.productProperty) && Objects.equals(productPropertyValue, that.productPropertyValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productProperty, productPropertyValue);
    }
}
