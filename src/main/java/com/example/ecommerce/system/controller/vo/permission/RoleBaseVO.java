package com.example.ecommerce.system.controller.vo.permission;

import com.example.ecommerce.system.enums.UserType;
import lombok.Data;

@Data
public class RoleBaseVO {
    private String name;
    private UserType userType;
}
