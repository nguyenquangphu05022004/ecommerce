package com.example.ecommerce.frame.websocket.config;

import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.frame.web.config.WebProperties;
import com.example.ecommerce.frame.websocket.core.AuthenticationToken;
import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import com.example.ecommerce.system.service.authen.AuthTokenService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Collections;
import java.util.Optional;

@Configuration
@AllArgsConstructor
@EnableWebSocketMessageBroker
@EnableConfigurationProperties(WebSocketProperties.class)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final WebSocketProperties webSocketProperties;
    private final AuthTokenService authTokenService;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(webSocketProperties.getEndpoint())
                .setAllowedOrigins("http://localhost:4200/",
                        "http://localhost:4200",
                        "https://" + webSocketProperties.getUiDomain(),
                        "https://" + webSocketProperties.getUiDomain() + "/")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes(webSocketProperties.getAppPrefix());
        registry.enableSimpleBroker(webSocketProperties.getBrokerPrefix());
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
                if (accessor.getCommand().equals(StompCommand.CONNECT)) {
                    Optional.ofNullable(accessor.getNativeHeader("Authorization"))
                            .ifPresent(ah -> {
                                String token = SecurityUtils.obtainToken(ah.get(0));
                                AuthenticationToken jwtAuthentication = getJWTAuthenticationToken(token);
                                accessor.setUser(jwtAuthentication);
                            });
                }
                return message;
            }
        });
    }

    private AuthenticationToken getJWTAuthenticationToken(String token) {
        AccessToken accessToken = this.authTokenService.getAccessToken(token);
        AuthenticationToken authenticationToken = new AuthenticationToken(
                Collections.emptyList(), token, accessToken.getUserMember()
        );
        return authenticationToken;
    }

}
