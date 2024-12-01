package com.example.ecommerce.system.dal.redis.dao;

import com.example.ecommerce.frame.common.date.DateUtils;
import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.system.dal.dataobject.auth.AccessToken;
import com.example.ecommerce.system.dal.redis.AuthTokenConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.ecommerce.system.dal.redis.AuthTokenConstant.ACCESS_TOKEN;

@Component
@RequiredArgsConstructor
public class RedisAuthTokenDao {
    private final RedisTemplate<String, String> redisTemplate;

    public AccessToken getAccessToken(String accessToken) {
        String key = getKey(accessToken);
        if(redisTemplate.hasKey(key)) {
            return JsonUtils.parseObject(redisTemplate.opsForValue().get(key), new TypeReference<AccessToken>() {});
        }
        return null;
    }

    public void setAccessToken(AccessToken accessToken) {
        String key = getKey(accessToken.getAccessToken());
        Long time = (DateUtils.of(accessToken.getExpires()).getTime() - new Date().getTime())/1000;
        if(time > 0) {
            accessToken = accessToken.toBuilder().modifiedBy(null)
                    .createdBy(null).createdDate(null).modifiedDate(null)
                    .userMember(accessToken.getUserMember().toBuilder().createdDate(null).modifiedDate(null).build()).build();
            redisTemplate.opsForValue().set(key, JsonUtils.write(accessToken), time, TimeUnit.SECONDS);
        }
    }

    public void deleteAccessToken(List<String> accessTokens) {
        List<String> keys = accessTokens.stream().map(this::getKey).toList();
        this.redisTemplate.delete(keys);
    }

    private String getKey(String accessToken) {
        return String.format(ACCESS_TOKEN, accessToken);
    }
}
