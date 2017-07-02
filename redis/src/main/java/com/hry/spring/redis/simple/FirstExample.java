package com.hry.spring.redis.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class FirstExample {
    @Autowired
    private RedisTemplate<String, String> template;

    public void save(String userId, String value) {
        template.boundListOps(userId).leftPush(value);
    }
}
