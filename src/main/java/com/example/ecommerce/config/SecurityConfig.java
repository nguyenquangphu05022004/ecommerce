package com.example.ecommerce.config;

import com.example.ecommerce.common.enums.Permission;
import com.example.ecommerce.common.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request
                            .requestMatchers(HttpMethod.GET, SecurityUrlConstants.COMMON_URL)
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, SecurityUrlConstants.USER_URL)
                            .hasAnyRole(
                                    Role.ADMIN.name(),
                                    Role.VENDOR.name()
                            )
                            .requestMatchers(HttpMethod.POST, SecurityUrlConstants.USER_URL)
                            .hasAnyAuthority(
                                    Permission.ADMIN_CREATE.name(),
                                    Permission.VENDOR_CREATE.name()
                            )
                            .requestMatchers(HttpMethod.PUT, SecurityUrlConstants.USER_URL)
                            .hasAnyAuthority(
                                    Permission.ADMIN_UPDATE.name(),
                                    Permission.VENDOR_UPDATE.name()
                            )
                            .requestMatchers(HttpMethod.DELETE, SecurityUrlConstants.USER_URL)
                            .hasAnyAuthority(
                                    Permission.ADMIN_DELETE.name(),
                                    Permission.VENDOR_DELETE.name()
                            )
                            .anyRequest().authenticated();

                })
                .logout(logout -> {
                    logout.logoutUrl("/api/v1/auth/logout")
                            .addLogoutHandler(
                                    (request, response, authentication) ->
                                            SecurityContextHolder.clearContext()
                            );
                })
                .build();
    }
}

class SecurityUrlConstants {
    public static final String[] COMMON_URL = {
            "/api/v1/auth/**",
            "/api/v1/products/**",
            "/api/v1/categories/**",
            "/api/v1/vendors/**",
            "/api/v1/coupons/**",
            "/api/v1/stocks/**",
            "/api/v1/files/**"
    };
    public static final String USER_URL = "/api/v1/users/**";
}
