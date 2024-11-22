package com.example.ecommerce.system.dal.redis.dao;

import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import com.example.ecommerce.system.dal.redis.AuthTokenConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.example.ecommerce.system.dal.redis.AuthTokenConstant.ACCESS_TOKEN;

@Component
@RequiredArgsConstructor
public class RedisAuthTokenDao {
    @Value("${web.auth.access_token}")
    private Integer accessTokenTimeAlive;
    private final RedisTemplate<String, String> redisTemplate;

    public AccessToken getAccessToken(String accessToken) {
        String key = getKey(accessToken);
        return JsonUtils.parseObject(redisTemplate.opsForValue().get(key), new TypeReference<AccessToken>() {});
    }

    public void setAccessToken(AccessToken accessToken) {
        String key = getKey(accessToken.getAccessToken());
        accessToken = accessToken.toBuilder().modifiedBy(null).createdBy(null)
                .createdDate(null).modifiedDate(null).expires(null)
                .userMember(accessToken.getUserMember().toBuilder().createdDate(null).modifiedDate(null).build())
                .build();
        redisTemplate.opsForValue().set(key, JsonUtils.write(accessToken), accessTokenTimeAlive, TimeUnit.MINUTES);
    }

    private String getKey(String accessToken) {
        return String.format(ACCESS_TOKEN, accessToken);
    }
}
