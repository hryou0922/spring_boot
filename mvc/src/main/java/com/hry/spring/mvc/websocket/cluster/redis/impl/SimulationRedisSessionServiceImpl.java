package com.hry.spring.mvc.websocket.cluster.redis.impl;

import com.hry.spring.mvc.websocket.cluster.redis.IRedisSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Redis服务类：模拟分布式的数据存储
 *
 * Created by huangrongyou@yixin.im on 2018/7/16.
 */
@Component
public class SimulationRedisSessionServiceImpl implements IRedisSessionService {

    @Autowired
    private RedisTemplate<String, String> template;


    // key = 登录用户名称， value=websocket的sessionId
    private ConcurrentHashMap<String,String> redisHashMap = new ConcurrentHashMap<>(32);

    /**
     * 在缓存中保存用户和websocket sessionid的信息
     * @param name
     * @param wsSessionId
     */
    public void add(String name, String wsSessionId){
        BoundValueOperations<String,String> boundValueOperations = template.boundValueOps(name);
        boundValueOperations.set(wsSessionId,24 * 3600, TimeUnit.SECONDS);
    }

    /**
     * 从缓存中删除用户的信息
     * @param name
     */
    public boolean del(String name){
        return template.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] rawKey = template.getStringSerializer().serialize(name);
                return connection.del(rawKey) > 0;
            }
        }, true);
    }

    /**
     * 根据用户id获取用户对应的sessionId值
     * @param name
     * @return
     */
    public String get(String name){
        BoundValueOperations<String,String> boundValueOperations = template.boundValueOps(name);
        return boundValueOperations.get();
    }
}
