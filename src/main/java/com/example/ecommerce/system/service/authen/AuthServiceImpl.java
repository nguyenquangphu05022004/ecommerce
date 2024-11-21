package com.example.ecommerce.system.service.authen;

import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.system.controller.auth.vo.AuthLoginReqVO;
import com.example.ecommerce.system.controller.auth.vo.AuthLoginResVO;
import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.ecommerce.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.ErrorCodeConstants.PASSWORD_NOT_FOUND;
import static com.example.ecommerce.system.enums.ErrorCodeConstants.USERNAME_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthTokenService authTokenService;
    private final PasswordEncoder encoder;
    @Override
    public AuthLoginResVO login(AuthLoginReqVO reqVO) {
        User user = this.userRepository
                .findByUsernameIgnoreCase(reqVO.getUsername())
                .orElseThrow(() -> exception(USERNAME_NOT_FOUND));

        if(!encoder.matches(reqVO.getPassword(), user.getPassword())) {
            throw exception(PASSWORD_NOT_FOUND);
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
