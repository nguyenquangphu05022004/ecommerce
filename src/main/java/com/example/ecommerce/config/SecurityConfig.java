package com.example.ecommerce.config;

import com.example.ecommerce.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userService;

    @Autowired
    public SecurityConfig(UserDetailsService userService) {
        this.userService = userService;
    }
    private final org.apache.logging.log4j.Logger LOGGER =  LogManager.getLogger();

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // verify user(from browser with user in database if valid -> success else error
    public void config(AuthenticationManagerBuilder authenticationManager) {
        authenticationManager.authenticationProvider(authenticationProvider());
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
            request
                    .requestMatchers(HttpMethod.GET, "/shop", "/home",
                            "/sign-up", "/login", "/user/**")
                    .permitAll()
                    .requestMatchers("/admin/**").hasAuthority(Role.ADMIN.getAuthority())
                    .requestMatchers("/admin/vendor/**", "/vendors").hasAuthority(Role.VENDOR.getAuthority())
                    .requestMatchers(HttpMethod.POST, "/register").permitAll()
                    .anyRequest().authenticated();

        })
                .formLogin(form -> {
                    form.loginPage("/login")
                            .successForwardUrl("/home")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .loginProcessingUrl("/login")
                            .failureUrl("/login?error=true")
                            .permitAll();
                })
                .logout(logout -> {
                    logout.
                            logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .permitAll();
                })
                .rememberMe(httpSecurityRememberMeConfigurer -> {
                    httpSecurityRememberMeConfigurer
                            .rememberMeParameter("rememberMe");
                })
                .build();
    }



}
