package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "trackProductSellers")
@Getter
@Data
public class TrackProductSeller extends Base {
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer numberOfProductsSold;

    /**
     * Using trigger auto increase numberOfProductsSold
     * When status Bill to Success
     */

    /**
     * When create a product using trigger auto map
     * TrackProductSeller
     */

}
