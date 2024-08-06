package com.example.ecommerce.config;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getUsername() {
        Authentication authen = SecurityContextHolder.getContext().getAuthentication();
        if(!(authen instanceof AnonymousAuthenticationToken)) {
            return authen.getName();
        }
        return null;
    }
}
