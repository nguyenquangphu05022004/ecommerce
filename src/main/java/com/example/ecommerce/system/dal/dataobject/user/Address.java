package com.example.ecommerce.system.dal.dataobject.user;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "sys_address")
@Entity
public class Address extends BaseEntity {
    private Boolean defaultAddress;
    private String district;
    private String province;
    private String city;
    private String details;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserMember user;

    @Transient
    @JsonIgnore
    public String detailAddress() {
        return details +", " + district + ", " + city + ", " + province;
    }
}
