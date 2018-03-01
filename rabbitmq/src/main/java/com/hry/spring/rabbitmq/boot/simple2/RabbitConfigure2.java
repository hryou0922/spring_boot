package com.hry.spring.rabbitmq.boot.simple2;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置RabbitMQ中使用到队列、交换机、绑定等信息
 *
 * Created by huangrongyou@yixin.im on 2018/2/11.
 */
@Configuration
public class RabbitConfigure2 {

    // 队列名称
    public final static String SPRING_BOOT_QUEUE = "spring-boot-queue-2";
    // 交换机名称
    public final static String SPRING_BOOT_EXCHANGE = "spring-boot-exchange-2";
    // 绑定的值
    public static final String SPRING_BOOT_BIND_KEY = "spring-boot-bind-key-2";
}
