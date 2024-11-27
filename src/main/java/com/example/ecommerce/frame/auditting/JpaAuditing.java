package com.example.ecommerce.frame.auditting;

import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditing {

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> {
            Long loginUserMemberId = SecurityUtils.getLoginUserMemberId();
            return Optional.of(loginUserMemberId);
        };
    }

}
