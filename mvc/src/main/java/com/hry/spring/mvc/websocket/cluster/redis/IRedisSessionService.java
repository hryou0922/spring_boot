package com.hry.spring.mvc.websocket.cluster.redis;

/**
 * Redis服务类
 * Created by huangrongyou@yixin.im on 2018/7/16.
 */
public interface IRedisSessionService {
    /**
     * 在缓存中保存用户和websocket sessionid的信息
     * @param name
     * @param wsSessionId
     */
    void add(String name, String wsSessionId);

    /**
     * 从缓存中删除用户的信息
     * @param name
     */
    boolean del(String name);

    /**
     * 根据用户名称获取用户对应的sessionId值
     * @param name
     * @return
     */
    String get(String name);

}