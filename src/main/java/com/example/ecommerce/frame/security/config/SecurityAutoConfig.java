package com.example.ecommerce.frame.security.config;

import com.example.ecommerce.frame.security.core.service.SecurityService;
import com.example.ecommerce.system.service.permission.PermissionService;
import com.example.ecommerce.system.service.permission.RoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityAutoConfig {


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("ss")
    public SecurityService securityService(RoleService roleService, PermissionService permissionService) {
        return new SecurityService(permissionService, roleService);
    }

}
