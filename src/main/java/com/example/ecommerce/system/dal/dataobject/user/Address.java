package com.example.ecommerce.system.dal.dataobject.user;

import com.example.ecommerce.frame.auditting.BaseEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "sys_address")
public class Address extends BaseEntity {
    private String district;
    private String province;
    private String city;
    private String details;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserMember user;
}
