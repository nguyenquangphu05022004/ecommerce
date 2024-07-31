package com.example.ecommerce.web.websocket;

import com.example.ecommerce.domain.entities.auth.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthenticationToken extends AbstractAuthenticationToken  {
    private String token;
    private User principle;

    public JWTAuthenticationToken(Collection<? extends GrantedAuthority> authorities,
                                  String token,
                                  User principle) {
        super(authorities);
        this.token = token;
        this.principle = principle;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.principle;
    }


}
