package com.example.ecommerce.frame.websocket.core;

import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationToken extends AbstractAuthenticationToken  {
    private String token;
    private UserMember principle;

    public AuthenticationToken(Collection<? extends GrantedAuthority> authorities,
                               String token,
                               UserMember principle) {
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
