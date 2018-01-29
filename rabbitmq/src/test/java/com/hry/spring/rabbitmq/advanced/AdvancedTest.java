package com.hry.spring.rabbitmq.advanced;

import com.hry.spring.rabbitmq.BaseTest;
import com.hry.spring.rabbitmq.advanced.publisherconfirm.*;
import org.junit.Test;

/**
 * Created by huangrongyou@yixin.im on 2018/1/10.
 */
public class AdvancedTest extends BaseTest {

    @Test
    public void publisherconfirm_noPublisherConfirmSend() throws InterruptedException {
        /**
         * 测试场景1：正常发送，则消息会被消费端接收
         * 测试场景2：TransactionalSend中注释掉 channel.txCommit();，则消息消不会被发送到消费者
         */
        // 接收端
        executorService.submit(() -> {
            PublisherConfirmRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
        });
        Thread.sleep(5* 100);

        // 发送端
        executorService.submit(() -> {
            NoPublisherConfirmSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, 10);
        });
        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void publisherconfirm_transactional() throws InterruptedException {
        /**
         * 测试场景1：正常发送，则消息会被消费端接收
         * 测试场景2：TransactionalSend中注释掉 channel.txCommit();，则消息消不会被发送到消费者
         */
        // 接收端
        executorService.submit(() -> {
            PublisherConfirmRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
        });
        Thread.sleep(5* 100);

        // 发送端
        executorService.submit(() -> {
            TransactionalSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, 10);
        });
        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void publisherconfirm_simpleConfirm() throws InterruptedException {
        /**
         * 测试场景1：正常发送，则消息会被消费端接收
         * 测试场景2：TransactionalSend中注释掉 channel.txCommit();，则消息消不会被发送到消费者
         */
        // 接收端
        executorService.submit(() -> {
            PublisherConfirmRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
        });
        Thread.sleep(5* 100);

        // 发送端
        executorService.submit(() -> {
            SimpleConfirmSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, 10);
        });
        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void publisherconfirm_AsynConfirmSend() throws InterruptedException {
        /**
         * 测试场景1：正常发送，则消息会被消费端接收
         * 测试场景2：TransactionalSend中注释掉 channel.txCommit();，则消息消不会被发送到消费者
         */
        // 接收端
        executorService.submit(() -> {
            PublisherConfirmRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
        });
        Thread.sleep(5* 100);

        // 发送端
        executorService.submit(() -> {
            AsynConfirmSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, 10);
        });
        // sleep 10s
        Thread.sleep(10 * 1000);
    }
}
