package com.hry.spring.rabbitmq.boot.msgconvert;

import com.hry.spring.rabbitmq.boot.msgconvert.pojo.MsgContent1;
import com.hry.spring.rabbitmq.boot.msgconvert.pojo.MsgContent2;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by huangrongyou@yixin.im on 2018/2/11.
 */
@Component
public class SendMsgConvertMsg {

    // 此接口的默认实现是RabbitTemplate，目前只有一个实现，
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息
     *
     * @param msgContent
     */
    public void sendMsgContent1(MsgContent1 msgContent) {
     //   amqpTemplate.convertAndSend(RabbitMsgConvertConfigure.SPRING_BOOT_EXCHANGE, RabbitMsgConvertConfigure.SPRING_BOOT_BIND_KEY, msgContent);
        amqpTemplate.convertAndSend(RabbitMsgConvertConfigure.SPRING_BOOT_EXCHANGE, RabbitMsgConvertConfigure.SPRING_BOOT_BIND_KEY, msgContent );


    }

    /**
     * 发送消息
     * @param msgContent
     */
    public void sendMsgContent2(MsgContent2 msgContent) {
        //   amqpTemplate.convertAndSend(RabbitMsgConvertConfigure.SPRING_BOOT_EXCHANGE, RabbitMsgConvertConfigure.SPRING_BOOT_BIND_KEY, msgContent);
        amqpTemplate.convertAndSend(RabbitMsgConvertConfigure.SPRING_BOOT_EXCHANGE, RabbitMsgConvertConfigure.SPRING_BOOT_BIND_KEY, msgContent);
    }
}
