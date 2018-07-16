package com.hry.spring.mvc.websocket.cluster.config;

import com.hry.spring.mvc.websocket.cluster.redis.IRedisSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import java.security.Principal;

/**
 * 装饰 WebSocketHandler
 *  装饰WebSocketHandlerDecorator对象，在连接建立时，保存websocket的session id；在连接断开时，从缓存中删除用户的sesionId值
 * Created by huangrongyou@yixin.im on 2018/7/11.
 */
@Component
public class AuthWebSocketHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {
    private static final Logger log = LoggerFactory.getLogger(AuthWebSocketHandlerDecoratorFactory.class);

    @Autowired
    private IRedisSessionService redisSessionService;

    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                // 客户端与服务器端建立连接后，此处记录谁上线了
                Principal principal = session.getPrincipal();
                if(principal != null){
                    String username = principal.getName();
                    log.info("websocket online: " + username + " session " + session.getId());
                    redisSessionService.add(username, session.getId());
                }
                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                // 客户端与服务器端断开连接后，此处记录谁下线了
                Principal principal = session.getPrincipal();
                if(principal != null){
                    String username = session.getPrincipal().getName();
                    log.info("websocket offline: " + username);
                    redisSessionService.del(username);
                }
                super.afterConnectionClosed(session, closeStatus);
            }
        };
    }
}
