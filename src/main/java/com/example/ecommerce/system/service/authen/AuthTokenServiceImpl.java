package com.example.ecommerce.system.service.authen;

import com.example.ecommerce.frame.common.date.LocalDateTimeUtils;
import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import com.example.ecommerce.system.dal.dataobject.auth.RefreshToken;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.redis.dao.RedisAuthTokenDao;
import com.example.ecommerce.system.dal.repository.UserMemberRepository;
import com.example.ecommerce.system.dal.repository.auth.AccessTokenRepository;
import com.example.ecommerce.system.dal.repository.auth.RefreshTokenRepository;
import com.example.ecommerce.system.enums.SysErrorCodeConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.ecommerce.frame.common.date.LocalDateTimeUtils.isExpired;
import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.system.enums.SysErrorCodeConstants.REFRESH_TOKEN_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthTokenServiceImpl implements AuthTokenService{


    @Value("${web.auth.access_token}")
    private Integer accessTokenTimeAlive; //minutes
    @Value("${web.auth.refresh_token}")
    private Integer refreshTokenTimeAlive; //minutes

    private final AccessTokenRepository accessTokenRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserMemberRepository userMemberRepository;
    private final RedisAuthTokenDao authTokenDao;
    @Override
    @Transactional
    public AccessToken createAccessToken(Long userId) {
        UserMember userMember = this.userMemberRepository.findById(userId).get();
        RefreshToken refreshToken = buildRefreshToken(userMember);
        AccessToken accessToken = AccessToken.builder()
                .refreshToken(refreshToken.getRefreshToken())
                .expires(LocalDateTime.now().plusMinutes(accessTokenTimeAlive))
                .accessToken(UUID.randomUUID().toString())
                .userMember(userMember).build();
        this.refreshTokenRepository.save(refreshToken);
        this.accessTokenRepository.save(accessToken);
        this.authTokenDao.setAccessToken(accessToken);
        return accessToken;
    }


    private RefreshToken buildRefreshToken(UserMember userMember) {
        return RefreshToken.builder().refreshToken(UUID.randomUUID().toString())
                .expires(LocalDateTime.now().plusMinutes(refreshTokenTimeAlive))
                .userMember(userMember).build();
    }


    @Override
    public void deleteByAccessToken(String accessToken) {

    }

    @Override
    public AccessToken refreshAccessToken(String freshToken) {
        RefreshToken refreshToken = this.refreshTokenRepository.findByRefreshToken(freshToken)
                .orElseThrow(() -> exception(REFRESH_TOKEN_NOT_FOUND));
        if(isExpired(refreshToken.getExpires())) {
            throw exception(REFRESH_TOKEN_NOT_FOUND);
        }
        AccessToken accessToken = AccessToken.builder().userMember(refreshToken.getUserMember())
                .expires(LocalDateTime.now().plusMinutes(accessTokenTimeAlive))
                .accessToken(UUID.randomUUID().toString())
                .refreshToken(refreshToken.getRefreshToken())
                .build();
        this.accessTokenRepository.save(accessToken);
        return accessToken;
    }
}
