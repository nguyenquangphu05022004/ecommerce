package com.example.ecommerce.frame.security.core.filter;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import com.example.ecommerce.system.service.authen.AuthTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    private final AuthTokenService authTokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String accessToken = SecurityUtils.obtainToken(request);
        System.out.println("token: " + accessToken);
        AccessToken authAccessToken = authTokenService.getAccessToken(accessToken);
        if(authAccessToken != null && !DateTimeUtils.isExpired(authAccessToken.getExpires())) {
            SecurityUtils.setUserLogin(authAccessToken.getUserMember(), request);
        }
        filterChain.doFilter(request, response);
    }
}
