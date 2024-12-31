package com.example.ecommerce.frame.security.core.service;

import com.example.ecommerce.system.service.permission.PermissionService;
import com.example.ecommerce.system.service.permission.RoleService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityService {

    private final PermissionService permissionService;
    private final RoleService roleService;

    public boolean hasPermission(String permission) {
        return true;
//        return this.permissionService.hasPermission(permission);
    }

    public boolean hasRole(String role) {
        return true;
    }
}
