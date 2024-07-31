package com.example.ecommerce.config;

import com.example.ecommerce.domain.entities.auth.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class ApplicationWebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.nullDestMatcher().authenticated()
                .simpDestMatchers("app/**")
                .hasAnyAuthority(Role.ADMIN.name(), Role.USER.name(), Role.VENDOR.name())
                .simpSubscribeDestMatchers("/topic/**", "/queue/**", "/chat/**", "/user/**")
                .hasAnyAuthority(Role.ADMIN.name(), Role.USER.name(), Role.VENDOR.name())
                .anyMessage().denyAll();
    }
}
