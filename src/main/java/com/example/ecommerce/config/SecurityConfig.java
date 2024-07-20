package com.example.ecommerce.config;

import com.example.ecommerce.common.enums.Permission;
import com.example.ecommerce.common.enums.Role;
import com.example.ecommerce.jwt.JwtAuthentication;
import com.example.ecommerce.jwt.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthentication jwtAuthentication;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    public void config(AuthenticationManagerBuilder authenticationManager) {
        authenticationManager.authenticationProvider(authenticationProvider);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request
                            .requestMatchers(SecurityUrlConstants.LIST_URL_ACCESS_ALL_METHOD)
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, SecurityUrlConstants.WHILE_LIST)
                            .permitAll()
                            .requestMatchers(HttpMethod.POST, SecurityUrlConstants.WHILE_LIST)
                            .hasAnyAuthority(
                                    Role.ADMIN.name(),
                                    Role.VENDOR.name()
                            )
                            .requestMatchers(HttpMethod.GET, SecurityUrlConstants.USER_URL)
                            .hasAnyAuthority(
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
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                })
                .authenticationProvider(authenticationProvider)
                .logout(logout -> {
                    logout.logoutUrl("/api/v1/auth/logout")
                            .addLogoutHandler(
                                    (request, response, authentication) ->
                                            SecurityContextHolder.clearContext()
                            );
                })
                .addFilterBefore(jwtAuthentication, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

class SecurityUrlConstants {
    public static final String[] WHILE_LIST = {
            "/api/v1/products/**",
            "/api/v1/categories/**",
            "/api/v1/vendors/**",
            "/api/v1/coupons/**",
            "/api/v1/stocks/**",
            "/api/v1/files/**",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
    public static final String USER_URL = "/api/v1/users/**";
    public static final String[] LIST_URL_ACCESS_ALL_METHOD = {
            "/api/v1/shopping-cart/**",
            "/api/v1/auth/**",
            "/api/v1/orders/**",
            "/api/v1/products/search"
    };
}
