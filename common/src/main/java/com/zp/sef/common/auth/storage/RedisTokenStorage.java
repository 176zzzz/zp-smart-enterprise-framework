package com.zp.sef.common.auth.storage;

import com.zp.sef.common.model.constant.SecurityConstants;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author zp
 * @version $Id$
 */
@Service
public class RedisTokenStorage implements TokenStorage {

    private static final String TOKEN_PREFIX = "srf-token:";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void store(String userId, String token) {
        String key = TOKEN_PREFIX + userId;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, token, SecurityConstants.EXPIRATION_TIME, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isValid(String userId, String token) {
        String key = TOKEN_PREFIX + userId;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String storedToken = operations.get(key);
        return storedToken != null && storedToken.equals(token);
    }

    @Override
    public void renew(String userId, String token) throws Exception {
        String key = TOKEN_PREFIX + userId;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String storedToken = operations.get(key);
        if (storedToken == null || !storedToken.equals(token)) {
            throw new Exception("Token不存在或已过期");
        }
        operations.set(key, token, SecurityConstants.EXPIRATION_TIME, TimeUnit.SECONDS);
    }
}