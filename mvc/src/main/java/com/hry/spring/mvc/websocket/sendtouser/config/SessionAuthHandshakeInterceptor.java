package com.hry.spring.mvc.websocket.sendtouser.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 自定义的握手拦截，获取已登录的User
 * Created by huangrongyou@yixin.im on 2018/7/10.
 */
@Component
public class SessionAuthHandshakeInterceptor implements HandshakeInterceptor {
    private static final Logger log = LoggerFactory.getLogger(SessionAuthHandshakeInterceptor.class);


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        HttpSession httpSession = getSession(request);
        String user = (String)httpSession.getAttribute("loginName");

        if(StringUtils.isEmpty(user)){
            log.error("未登录系统，禁止登录websocket!");
            return false;
        }
        log.info("login = " + user);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        // 删除值
        HttpSession httpSession = getSession(request);
        httpSession.removeAttribute("loginName");
    }

    // 参考 HttpSessionHandshakeInterceptor
    private HttpSession getSession(ServerHttpRequest request) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            return serverRequest.getServletRequest().getSession(false);
        }
        return null;
    }
}
