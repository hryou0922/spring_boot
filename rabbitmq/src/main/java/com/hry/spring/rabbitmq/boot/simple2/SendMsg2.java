package com.hry.spring.rabbitmq.boot.simple2;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by huangrongyou@yixin.im on 2018/2/11.
 */
@Component
public class SendMsg2 {

    // 此接口默认实现只有一个，且是RabbitAdmin，通过源码发现其内部实现实际是RabbitTemplate。所以AmqpAdmin和AmqpTemplate当前两者本质是相同的
    @Autowired
    private AmqpAdmin amqpAdmin;

    // 此接口的默认实现是RabbitTemplate，目前只有一个实现，
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息
     *
     * @param msgContent
     */
    public void send_2(String msgContent) {
        amqpTemplate.convertAndSend(RabbitConfigure2.SPRING_BOOT_EXCHANGE, RabbitConfigure2.SPRING_BOOT_BIND_KEY, msgContent);
    }
}
