package com.hry.spring.redis.simple;

import java.net.URL;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
 
public class FirstExample {
	 // inject the actual template
    @Autowired
    private RedisTemplate<String, String> template;

    // inject the template as ListOperations
    // can also inject as Value, Set, ZSet, and HashOperations
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public void addLink(String userId, URL url) {
        listOps.leftPush(userId, url.toExternalForm());
        // or use template directly
        template.boundListOps(userId).leftPush(url.toExternalForm());
        
        org.springframework.data.redis.connection.jedis.JedisConnectionFactory j = new org.springframework.data.redis.connection.jedis.JedisConnectionFactory();
        
    }
}
