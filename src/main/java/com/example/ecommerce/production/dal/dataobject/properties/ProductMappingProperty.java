package com.example.ecommerce.production.dal.dataobject.properties;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "production_product_mapping_property")
@NoArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class ProductMappingProperty extends BaseEntity {

    /**
     * Cap Pair(productId, productType): xac dinh loai property
     * Vi du:
     * (productId = productSpuId, SPU): Noi ve chi tiet cua san pham
     *              -> Hang da su dung: Yes
     *              -> Co bao hanh: Yes
     * (productId = productSkuId, SKU): Dung de xac dinh 1 product sku cu the
     *                 -> Ao liver pool, red, 43
     *                 -> Mau sac: Do
     *                 -> Kich co: 43
     * (productId = productSpuId, COMMENT): Nguoi ban cai san pham co productSpuId
     * se tao cac danh gia khac nhau ve san pham nhu la:
     *              -> Chat luong san pham: Khong duoc tot lam
     *              -> Tinh nang noi bat: ....
     *              -> ...........
     */
    private Long productId;
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private ProductProperty productProperty;
    @ManyToOne
    @JoinColumn(name = "property_value_id")
    private ProductPropertyValue productPropertyValue;

    public static enum ProductType {
        SPU, SKU, COMMENT
    }
}
