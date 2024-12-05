package com.example.ecommerce.product.dal.dataobject.spu;

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

/**
 * Table: Storage details about Product
 */
@Entity
@Table(name = "production_product_spu_details")
@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
public class ProductSpuDetail extends BaseEntity {
    /**
     * product spu
     */
    @ManyToOne
    @JoinColumn(name = "product_spu_id")
    private ProductSpu productSpu;

    /**
     * Details product here is a question yes-no. Example
     * Nguon goc: Nuoc ngoai
     * Co bao hanh: Co
     * Gui tu: Ha noi
     * Kieu ket noi: Khong day. ...
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
