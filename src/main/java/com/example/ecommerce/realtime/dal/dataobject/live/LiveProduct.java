package com.example.ecommerce.realtime.dal.dataobject.live;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "realtime_live_product")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class LiveProduct extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_spu_id")
    private ProductSpu productSpu;

    /**
     * Hien thi o khung hinh livestream.
     * Moi san pham chi co the duoc hien thi 1 lan,
     * theo thu tu cua nguoi livestream muon hien thi
     */
    private Boolean display;

    /**
     * Hien thi o khung chat
     */
    private Boolean pin;

    @ManyToOne
    @JoinColumn(name = "livestream_id")
    private LiveStream liveStream;
}
