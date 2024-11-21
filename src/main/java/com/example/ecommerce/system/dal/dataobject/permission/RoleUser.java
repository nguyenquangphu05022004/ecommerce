package com.example.ecommerce.system.dal.dataobject.permission;


import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class RoleUser extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
