package com.hry.spring.rabbitmq.boot.raw;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by huangrongyou@yixin.im on 2018/2/27.
 */
@Component
public class ReceiveRawMsg {

    /**
     * 获取信息:
     *  queue也可以支持RabbitMQ中对队列的模糊匹配
     * @param content
     */
    @RabbitListener(queues = RabbitRawConfigure.SPRING_BOOT_QUEUE)
    public void receive_1(String content) {
        // ...
        System.out.println("[ReceiveMsg-1] receive msg: " + content);
    }

}
