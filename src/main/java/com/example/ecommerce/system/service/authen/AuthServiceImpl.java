package com.example.ecommerce.system.service.authen;

import com.example.ecommerce.system.controller.auth.vo.AuthLoginReqVO;
import com.example.ecommerce.system.controller.auth.vo.AuthLoginResVO;
import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.service.user.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.ACCOUNT_IS_LOCKED;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.PASSWORD_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMemberService userMemberService;
    private final AuthTokenService authTokenService;

    @Override
    public AuthLoginResVO login(AuthLoginReqVO reqVO) {
        UserMember user = userMemberService.getUserMemberByUsername(reqVO.getUsername());
        if(userMemberService.isPasswordMatch(reqVO.getPassword(), user.getPassword())) {
            throw exception(PASSWORD_NOT_FOUND);
        }
        if(user.isLocked()) {
            throw exception(ACCOUNT_IS_LOCKED);
        }
        AccessToken accessToken = this.authTokenService.createAccessToken(user.getId());
        return new AuthLoginResVO(accessToken);
    }

    @Override
    public void logout(String accessToken) {
        this.authTokenService.deleteByAccessToken(accessToken);
    }

    @Override
    public AuthLoginResVO refreshToken(String refreshToken) {
        return new AuthLoginResVO(this.authTokenService.refreshAccessToken(refreshToken));
    }
}
