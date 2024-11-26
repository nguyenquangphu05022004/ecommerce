package com.example.ecommerce.production.dal.dataobject.sku;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.production.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.production.dal.dataobject.properties.ProductPropertyValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@Table(name = "production_product_sku_properties")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ProductSkuProperty extends BaseEntity {
    /**
     * Product sku(variety product spu)
     */
    @ManyToOne
    @JoinColumn(name = "product_sku_id")
    private ProductSku productSku;

    /**
     * Options product sku, you must select all options to be able to purchase or add to cart
     * Example:
     *    Product spu: Ao liverpool
     *    Product sku:
     *          1, size: 40, color: white, price: 5000 -> select both size and color to be able to see price
     *    At here: size(property) - 40(propertyValue), others are the same
     */

    /**
     * Key
     */
    @ManyToOne
    @JoinColumn(name = "product_property_id")
    private ProductProperty productProperty;
    /**
     * Value
     */
    @ManyToOne
    @JoinColumn(name = "product_property_value_id")
    private ProductPropertyValue productPropertyValue;

}
