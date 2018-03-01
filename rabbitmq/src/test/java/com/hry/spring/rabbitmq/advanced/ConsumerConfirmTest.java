package com.hry.spring.rabbitmq.advanced;

import com.hry.spring.rabbitmq.BaseTest;
import com.hry.spring.rabbitmq.advanced.consumer.ConsumerRecv;
import com.hry.spring.rabbitmq.advanced.consumer.ConsumerSend;
import com.hry.spring.rabbitmq.advanced.consumer.ReturnListenerSend;
import com.hry.spring.rabbitmq.advanced.consumerconfirm.ConsumerConfirmRecv;
import com.hry.spring.rabbitmq.advanced.consumerconfirm.ConsumerConfirmSend;
import com.hry.spring.rabbitmq.advanced.publisherconfirm.*;
import org.junit.Test;

/**
 * Created by huangrongyou@yixin.im on 2018/1/10.
 */
public class ConsumerConfirmTest extends BaseTest {
    private static final String routingKey = "consumer-confirm";

    @Test
    public void ConsumerConfirmRecv() throws InterruptedException {

        // 生产者端
        executorService.submit(() -> {
            ConsumerConfirmSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey,1);
        });
        Thread.sleep(5* 100);

        // 消费者端
        executorService.submit(() -> {
            ConsumerConfirmRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey);
        });
        Thread.sleep(5* 100);

        Thread.sleep(10 * 1000);
    }

//    @Test
//    public void ConsumerConfirmRecv2() throws InterruptedException {
//        // 生产者端
// //       ConsumerConfirmSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey,1);
//
//        // 消费者端
//        ConsumerConfirmRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey);
//
//        Thread.sleep(90 * 1000);
//    }
}
