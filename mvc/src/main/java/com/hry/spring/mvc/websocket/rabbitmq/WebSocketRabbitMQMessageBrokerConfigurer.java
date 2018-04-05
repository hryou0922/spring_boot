package com.hry.spring.mvc.websocket.rabbitmq;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
// 此注解开使用STOMP协议来传输基于消息代理的消息，此时可以在@Controller类中使用@MessageMapping
@EnableWebSocketMessageBroker
public class WebSocketRabbitMQMessageBrokerConfigurer extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 注册 Stomp的端点
         *
         * addEndpoint：添加STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址
         * withSockJS：指定端点使用SockJS协议
          */
        registry.addEndpoint("/websocket-rabbitmq").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /**
         * 配置消息代理
         * 使用RabbitMQ做为消息代理，替换默认的Simple Broker
         * 设置目的地前缀
         */
        registry
                //.setApplicationDestinationPrefixes("/demo")
                .enableStompBrokerRelay("/exchange","/topic","/queue","/amq/queue")
                .setRelayHost("192.168.0.113")
              //  .setRelayHost("10.242.72.29")
                .setClientLogin("hry")
                .setClientPasscode("hry")
                .setSystemLogin("hry")
                .setSystemPasscode("hry")
                .setSystemHeartbeatSendInterval(5000)
                .setSystemHeartbeatReceiveInterval(4000);
                ;

  //      registry.enableSimpleBroker("/topic","/queue","/rabbitmq");
}
}
