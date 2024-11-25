package com.example.ecommerce.frame.security.core.utils;

import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Collections;

public class SecurityUtils {

    private static final String AUTH = "Authorization";
    private static final String TOKEN_TYPE = "UUID";

    public static String obtainToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH);
        if(authHeader == null) return null;
        if(!authHeader.startsWith(TOKEN_TYPE)) return null;
        return authHeader.substring(5);
    }

    public static UserMember getLoginUserMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return (UserMember) authentication;
        }
        return null;
    }
    public static Long getLoginUserMemberId() {
        UserMember userMember = getLoginUserMember();
        return userMember != null ? userMember.getId() : null;
    }



    private static Authentication setAuthentication(UserMember userMember, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userMember, null, Collections.emptyList()
        );
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        return authenticationToken;
    }

    public static void setUserLogin(UserMember userMember, HttpServletRequest request) {
        Authentication authentication = setAuthentication(userMember, request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
