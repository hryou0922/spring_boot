package com.hry.spring.rabbitmq.boot.msgconvert;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置RabbitMQ中使用到队列、交换机、绑定等信息
 *
 * Created by huangrongyou@yixin.im on 2018/2/11.
 */
@Configuration
public class RabbitMsgConvertConfigure {

    // 队列名称
    public final static String SPRING_BOOT_QUEUE = "spring-boot-queue-msg-convert";
    // 交换机名称
    public final static String SPRING_BOOT_EXCHANGE = "spring-boot-exchange-msg-convert";
    // 绑定的值
    public static final String SPRING_BOOT_BIND_KEY = "spring-boot-bind-key-msg-convert";


    // === 在RabbitMQ上创建queue,exchange,binding 方法一：通过@Bean实现 begin ===
    /**
     * 定义队列：
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(SPRING_BOOT_QUEUE, false);
    }

    /**
     * 定义交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(SPRING_BOOT_EXCHANGE);
    }

    /**
     * 定义绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SPRING_BOOT_BIND_KEY );
    }

    /**
     * 定义消息转换实例
     * @return
     */
    @Bean
    MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // === 如果默认的SimpleMessageListenerContainer不符合我们的要求，我们也可以通过如下的方式创建新的SimpleMessageListenerContainer===
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setMessageConverter().
//        container.setConcurrentConsumers(10);
//        return container;
//    }


//    @Bean
//    MessageListenerAdapter listenerAdapter(ProductMessageListener receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }

}
