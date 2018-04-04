package com.hry.spring.rabbitmq.boot.simple1;

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
public class ReceiveMsg1 {

    /**
     * 获取信息:
     *  queue也可以支持RabbitMQ中对队列的模糊匹配
     * @param content
     */
    @RabbitListener(queues = RabbitConfigure1.SPRING_BOOT_QUEUE)
    public void receive_1(String content) {
        // ...
        System.out.println("[ReceiveMsg-1] receive msg: " + content);
    }

}
