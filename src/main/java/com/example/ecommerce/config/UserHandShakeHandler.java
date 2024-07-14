package com.example.ecommerce.config;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.repository.UserRepository;
import com.sun.security.auth.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class UserHandShakeHandler extends DefaultHandshakeHandler {

    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if(redisTemplate.opsForHash().hasKey("WS", SecurityUtils.username())) {
            return new UserPrincipal(String.valueOf(redisTemplate.opsForHash().get("WS", SecurityUtils.username())));
        }
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.username())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "You aren't login, please login before using the resources"));
        redisTemplate.opsForHash().put("WS", SecurityUtils.username(), user.getId());
        log.info("User opened the page {}",user.getId());
        return new UserPrincipal(String.valueOf(user.getId()));
    }
}
