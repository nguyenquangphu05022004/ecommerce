package com.example.ecommerce.system.dal.dataobject.user;


import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sys_customers")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
public class Customer extends BaseEntity {
    /**
     * cartIds: Long[]
     * orderIds: Long[]
     * favoriteProductSkuIds: Long[]
     */

    @OneToOne
    @JoinColumn(name = "user_member_id", unique = true)
    private UserMember userMember;


}
