package com.example.ecommerce.system.dal.dataobject.user;


import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "sys_customers")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@DiscriminatorValue("SELLER")
public class Customer extends UserMember {
    /**
     * cartIds: Long[]
     * orderIds: Long[]
     * favoriteProductSkuIds: Long[]
     */

    @OneToOne
    @JoinColumn(name = "user_member_id", unique = true)
    private UserMember userMember;


}
