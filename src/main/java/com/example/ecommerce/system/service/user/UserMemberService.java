package com.example.ecommerce.system.service.user;

import com.example.ecommerce.system.controller.user.vo.UserMemberCreateReqVO;
import com.example.ecommerce.system.controller.user.vo.UserMemberUpdatePasswordReqVO;
import com.example.ecommerce.system.controller.user.vo.UserMemberUpdateReqVO;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;

public interface UserMemberService {
    UserMember createUser(UserMemberCreateReqVO reqVO);
    UserMember updateUser(Long userId, UserMemberUpdateReqVO reqVO);
    UserMember updatePassword(Long userId, UserMemberUpdatePasswordReqVO reqVO);
    boolean isPasswordMatch(String raw, String encode);
    UserMember getUserMemberById(Long userId);
    UserMember getUserMemberByUsername(String username);
    void updateStatusAccount(Long userId, boolean locked);
}
