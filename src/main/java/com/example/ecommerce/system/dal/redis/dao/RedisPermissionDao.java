package com.example.ecommerce.system.dal.redis.dao;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.system.dal.dataobject.permission.MenuRole;
import com.example.ecommerce.system.dal.dataobject.permission.Role;
import com.example.ecommerce.system.dal.redis.PermissionConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.example.ecommerce.system.dal.redis.PermissionConstant.USER_LIST_ROLE;

@Component
@RequiredArgsConstructor
public class RedisPermissionDao {
    private final RedisTemplate<String, String> redisTemplate;


    public <T> List<T> getList(String key, String hashKey) {
        Object object = this.redisTemplate.opsForHash().get(key, hashKey);
        if(object != null) {
            return JsonUtils.parseObject((String) object, new TypeReference<List<T>>() {});
        }
        return Collections.emptyList();
    }

    public <T> List<T> getList(String key) {
        String object = this.redisTemplate.opsForValue().get(key);
        if(object == null) {
            return Collections.emptyList();
        }
        return JsonUtils.parseObject(object, new TypeReference<List<T>>() {});
    }


    public void setValue(String key, String hashKey, Collection<? extends BaseEntity> coll) {
        CollUtils.convertList(coll, v -> {
            v.setNull();
            return null;
        });
        this.redisTemplate.opsForHash().put(key, hashKey, JsonUtils.write(coll));
    }
    public  void setValue(String key,Collection<? extends BaseEntity> coll) {
        CollUtils.convertList(coll, v -> {
            v.setNull();
            return null;
        });
        this.redisTemplate.opsForValue().set(key, JsonUtils.write(coll));
    }

    public void clear(String key, String hashKey) {

    }

}
