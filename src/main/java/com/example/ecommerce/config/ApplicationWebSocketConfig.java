package com.example.ecommerce.config;

import com.example.ecommerce.config.jwt.JwtAuthentication;
import com.example.ecommerce.config.jwt.JwtService;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.web.websocket.JWTAuthenticationToken;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.socket.config.annotation.*;

import java.util.Optional;

@Configuration
@AllArgsConstructor
@EnableWebSocketMessageBroker
public class ApplicationWebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final UserHandShakeHandler userHandShakeHandler;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setHandshakeHandler(userHandShakeHandler)
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
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
                                String bearerToken = ah.get(0).replace("Bearer ", "");
                                JWTAuthenticationToken jwtAuth = getJWTAuthenticationToken(bearerToken);
                                accessor.setUser(jwtAuth);
                            });
                }
                return message;
            }
        });
    }

    private JWTAuthenticationToken getJWTAuthenticationToken(String token) {
        if(token != null) {
            String username = this.jwtService.extractUsername(token);
            User user = userRepository.findByUsernameIgnoreCase(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            JWTAuthenticationToken jwtAuthentication = new JWTAuthenticationToken(
                    user.getAuthorities(),
                    token,
                    user
            );

            jwtAuthentication.setAuthenticated(true);
            return jwtAuthentication;
        }
        return null;
    }
}
