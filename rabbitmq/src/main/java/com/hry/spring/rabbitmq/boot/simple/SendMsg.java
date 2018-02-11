package com.hry.spring.rabbitmq.boot.simple;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by huangrongyou@yixin.im on 2018/2/11.
 */
@Component
public class SendMsg {
    @Autowired
    private AmqpAdmin amqpAdmin;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendProductMessage(String id) {
//        Map<String, String> actionmap = new HashMap<>();
//        actionmap.put("id", id);
//        log.info("Sending the index request through queue message");
//        rabbitTemplate.convertAndSend(SpringBootRabbitMQApplication.SFG_MESSAGE_QUEUE, actionmap);

        amqpTemplate.send();
    }
}
