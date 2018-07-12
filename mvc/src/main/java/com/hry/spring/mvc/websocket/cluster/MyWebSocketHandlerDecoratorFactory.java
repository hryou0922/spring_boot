package com.hry.spring.mvc.websocket.cluster;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

/**
 * 装饰 WebSocketHandler
 * Created by huangrongyou@yixin.im on 2018/7/11.
 */
//@Component
public class MyWebSocketHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {
    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return null;
    }
//    private static final Logger log = LoggerFactory.getLogger(MyWebSocketHandlerDecoratorFactory.class);
//
//    @Autowired
//    private IWsSessionCacheService wsSessionCacheService;
//
//    @Override
//    public WebSocketHandler decorate(WebSocketHandler handler) {
//        return new WebSocketHandlerDecorator(handler) {
//            @Override
//            public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
//                // 客户端与服务器端建立连接后，此处记录谁上线了
//                Principal principal = session.getPrincipal();
//                if(principal != null){
//                    String username = principal.getName();
//                    log.info("websocket online: " + username + " session " + session.getId());
//                    wsSessionCacheService.save(username, session.getId());
//                }
//                super.afterConnectionEstablished(session);
//            }
//
//            @Override
//            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//                // 客户端与服务器端断开连接后，此处记录谁下线了
//                Principal principal = session.getPrincipal();
//                if(principal != null){
//                    String username = session.getPrincipal().getName();
//                    log.info("websocket offline: " + username);
//                    wsSessionCacheService.removeItem(username, session.getId());
//                }
//                super.afterConnectionClosed(session, closeStatus);
//            }
//
//            @Override
//            public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//                log.error("handleTransportError: ================ {}", exception );
//                super.handleTransportError(session, exception);
//            }
//        };
//    }
}
