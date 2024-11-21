package com.example.ecommerce.system.dal.dataobject.user;

import com.example.ecommerce.domain.entities.BaseEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UserMember extends BaseEntity {
    private String username;
    private String password;
    private boolean isOnline;

    private String firstName;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
