package com.example.ecommerce.system.dal.dataobject.user;


import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sys_customers")
public class Customer extends BaseEntity {
    /**
     * cartIds: Long[]
     * orderIds: Long[]
     * favoriteProductSkuIds: Long[]
     */
}
