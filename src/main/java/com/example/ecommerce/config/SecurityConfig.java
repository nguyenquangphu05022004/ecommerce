package com.example.ecommerce.config;

import com.example.ecommerce.domain.entities.auth.Permission;
import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.config.jwt.JwtAuthentication;
import com.example.ecommerce.config.jwt.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${api.version}")
    private String apiVersion;
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
                            .requestMatchers(
                                    apiVersion + "/shopping-cart/**",
                                    apiVersion + "/auth/**",
                                    apiVersion + "/products/search")
                            .permitAll()
                            .requestMatchers(apiVersion + "/orders/**")
                            .authenticated()
                            .requestMatchers( 
                                    apiVersion + "/products/**",
                                    apiVersion + "/categories/**",
                                    apiVersion + "/users/vendors/**",
                                    apiVersion + "/files/**")
                            .permitAll()
                            .requestMatchers(HttpMethod.GET, apiVersion + "/users/**")
                            .hasAnyAuthority(
                                    Role.ADMIN.name(),
                                    Role.VENDOR.name()
                            )
                            .requestMatchers(HttpMethod.POST, apiVersion + "/users/**")
                            .hasAnyAuthority(
                                    Permission.ADMIN_CREATE.name(),
                                    Permission.VENDOR_CREATE.name()
                            )
                            .requestMatchers(HttpMethod.PUT, apiVersion + "/users/**")
                            .hasAnyAuthority(
                                    Permission.ADMIN_UPDATE.name(),
                                    Permission.VENDOR_UPDATE.name()
                            )
                            .requestMatchers(HttpMethod.DELETE, apiVersion + "/users/**")
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
                    logout.logoutUrl(apiVersion + "/auth/logout")
                            .addLogoutHandler(
                                    (request, response, authentication) ->
                                            SecurityContextHolder.clearContext()
                            );
                })
                .addFilterBefore(jwtAuthentication, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
