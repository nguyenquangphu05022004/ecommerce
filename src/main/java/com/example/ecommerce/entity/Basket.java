package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.View;

//@Entity
//@Data
//@NoArgsConstructor
//@Getter
public class Basket extends Base{

    /*
        basket:
            id, product
     */
    private Product product;
}
