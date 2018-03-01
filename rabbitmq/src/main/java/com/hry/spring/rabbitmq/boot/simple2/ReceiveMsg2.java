package com.hry.spring.rabbitmq.boot.simple2;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by huangrongyou@yixin.im on 2018/2/27.
 */
@Component
public class ReceiveMsg2 {

    /**
     * === 在RabbitMQ上创建queue,exchange,binding 方法二：直接在@RabbitListener声明 begin ===
     * 接收
     * @param content
     */
    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
            bindings = @QueueBinding(
            value = @Queue(value = RabbitConfigure2.SPRING_BOOT_QUEUE+"3", durable = "true", autoDelete="true"),
            exchange = @Exchange(value = RabbitConfigure2.SPRING_BOOT_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = RabbitConfigure2.SPRING_BOOT_BIND_KEY)
    )
    public void receive_2(String content) {
        // ...
        System.out.println("[ReceiveMsg-2] receive msg: " + content);
    }

}
