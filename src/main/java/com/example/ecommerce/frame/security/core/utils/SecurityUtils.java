package com.example.ecommerce.frame.security.core.utils;

import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {


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
}
