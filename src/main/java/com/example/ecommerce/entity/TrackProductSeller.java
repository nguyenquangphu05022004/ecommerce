package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "trackProductSellers")
@Getter
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class TrackProductSeller extends Base {
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer numberOfProductsSold;

}
