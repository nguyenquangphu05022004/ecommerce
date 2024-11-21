package com.example.ecommerce.system.dal.dataobject.permission;

import com.example.ecommerce.system.enums.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class Role {
    private String name;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
