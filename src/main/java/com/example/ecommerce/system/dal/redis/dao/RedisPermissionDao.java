package com.example.ecommerce.system.dal.redis.dao;

import com.example.ecommerce.system.dal.dataobject.permission.Role;
import com.example.ecommerce.system.dal.redis.PermissionConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.ecommerce.system.dal.redis.PermissionConstant.USER_LIST_ROLE;

@Component
@RequiredArgsConstructor
public class RedisPermissionDao {
    private final RedisTemplate<String, String> redisTemplate;


    public List<Role> getListRole(Long userId) {
        this.redisTemplate.opsForHash().get(USER_LIST_ROLE, userId);
        return null;
    }


}
