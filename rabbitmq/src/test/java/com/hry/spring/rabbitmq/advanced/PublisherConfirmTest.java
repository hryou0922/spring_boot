package com.hry.spring.rabbitmq.advanced;

import com.hry.spring.rabbitmq.BaseTest;
import com.hry.spring.rabbitmq.advanced.consumer.ReturnListenerSend;
import com.hry.spring.rabbitmq.advanced.publisherconfirm.*;
import com.hry.spring.rabbitmq.advanced.consumer.ConsumerRecv;
import com.hry.spring.rabbitmq.advanced.consumer.ConsumerSend;
import org.junit.Test;

/**
 * Created by huangrongyou@yixin.im on 2018/1/10.
 */
public class PublisherConfirmTest extends BaseTest {
    private static final String routingKey = "publisher-confirm";

    @Test
    public void publisherconfirm_noPublisherConfirmSend() throws InterruptedException {

        // 发送端
        executorService.submit(() -> {
            NoPublisherConfirmSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey,1);
        });
        Thread.sleep(5* 100);

        // 消费端
        executorService.submit(() -> {
            PublisherConfirmRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey);
        });
        Thread.sleep(5* 100);


        Thread.sleep(10 * 1000);
    }

    @Test
    public void publisherconfirm_transactional() throws InterruptedException {
        // 发送端
        executorService.submit(() -> {
            TransactionalSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey,1);
        });
        Thread.sleep(5* 100);
//        // 消费端
//        executorService.submit(() -> {
//            ConsumerRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey);
//        });
//        Thread.sleep(5* 100);

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void publisherconfirm_simpleConfirm() throws InterruptedException {

        // 发送端
        executorService.submit(() -> {
            SimpleConfirmSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey,1);
        });
        Thread.sleep(5* 100);

//        // 消费端
//        executorService.submit(() -> {
//            ConsumerRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey);
//        });
//        Thread.sleep(5* 100);

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void publisherconfirm_simpleConfirm_onlySend() throws InterruptedException {
//        // 消费端
//        executorService.submit(() -> {
//            ConsumerRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
//        });
//        Thread.sleep(5* 100);

        // 发送端
        executorService.submit(() -> {
            SimpleConfirmSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey, 10);
        });
        // sleep 10s
        Thread.sleep(80 * 1000);
    }

    @Test
    public void publisherconfirm_AsynConfirmSend() throws InterruptedException {
        // 发送端
        executorService.submit(() -> {
            AsynConfirmSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey, 10);
        });

        // 消费端
        executorService.submit(() -> {
            ConsumerRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey);
        });
        Thread.sleep(5* 100);

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void publisherconfirm_consumer() throws InterruptedException {
        // 发送端
        executorService.submit(() -> {
            ConsumerSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey, 1);
        });
        Thread.sleep(2* 100);
        // 消费端
        executorService.submit(() -> {
            ConsumerRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey);
        });
        Thread.sleep(5* 1000);
        // sleep 10s
        Thread.sleep(60 * 1000);
    }

    @Test
    public void publisherconfirm_mandatory() throws InterruptedException {
        // 发送端
        executorService.submit(() -> {
            ReturnListenerSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routingKey, 1);
        });
        // sleep 10s
        Thread.sleep(60 * 1000);
    }
}
