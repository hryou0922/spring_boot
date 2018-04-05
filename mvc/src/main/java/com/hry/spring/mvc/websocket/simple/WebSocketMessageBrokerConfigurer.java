package com.hry.spring.mvc.websocket.simple;

import com.hry.spring.mvc.websocket.simple.inceptor.MyChannelInterceptorAdapter;
import com.hry.spring.mvc.websocket.simple.inceptor.MyHandShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
// 此注解表示使用STOMP协议来传输基于消息代理的消息，此时可以在@Controller类中使用@MessageMapping
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfigurer extends AbstractWebSocketMessageBrokerConfigurer {

    @Autowired
    private MyHandShakeInterceptor myHandShakeInterceptor;

    @Autowired
    private MyChannelInterceptorAdapter myChannelInterceptorAdapter;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 注册 Stomp的端点
         *
         * addEndpoint：添加STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址
         * withSockJS：指定端点使用SockJS协议
          */
        registry.addEndpoint("/websocket-simple")
                .setAllowedOrigins("*") // 添加允许跨域访问
                .addInterceptors(myHandShakeInterceptor) // 添加自定义拦截
                .withSockJS();

        registry.addEndpoint("/websocket-simple-single").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {


        /**
         * 配置消息代理
         * 启动简单Broker，消息的发送的地址符合配置的前缀来的消息才发送到这个broker
         *  Enable a simple message broker and configure one or more prefixes to filte destinations targeting the broker
         */
        registry.enableSimpleBroker("/topic","/queue");
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        //registry.setUserDestinationPrefix("/user/");
        // 置客户端发送信息的路径的前缀
        // registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        ChannelRegistration channelRegistration = registration.setInterceptors(myChannelInterceptorAdapter);
        super.configureClientInboundChannel(registration);
    }
}
