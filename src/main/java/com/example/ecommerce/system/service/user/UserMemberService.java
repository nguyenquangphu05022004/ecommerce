package com.example.ecommerce.system.service.user;

import com.example.ecommerce.system.controller.app.user.vo.CustomerCreateReqVO;
import com.example.ecommerce.system.controller.admin.user.vo.SellerCreateReqVO;
import com.example.ecommerce.system.controller.app.user.vo.UserMemberUpdatePasswordReqVO;
import com.example.ecommerce.system.controller.app.user.vo.UserMemberUpdateReqVO;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;

public interface UserMemberService {
    UserMember createUserMember(CustomerCreateReqVO reqVO);
    UserMember createUserSeller(SellerCreateReqVO reqVO);

    UserMember updateUser(Long userId, UserMemberUpdateReqVO reqVO);
    UserMember updatePassword(Long userId, UserMemberUpdatePasswordReqVO reqVO);
    boolean isPasswordMatch(String raw, String encode);
    UserMember getUserMemberById(Long userId);
    UserMember getUserMemberByUsername(String username);
    void updateStatusAccount(Long userId, boolean locked);

    void updateUserOnline(String username, boolean isOnline);
}
