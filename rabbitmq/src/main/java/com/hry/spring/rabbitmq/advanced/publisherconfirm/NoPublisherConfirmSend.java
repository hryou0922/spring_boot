package com.hry.spring.rabbitmq.advanced.publisherconfirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 不增加作保机制保证发送的消息正常到达Broken
 */
public class NoPublisherConfirmSend {
        private final static String QUEUE_NAME = "publisherconfirm";

    public static void execute(String host, String userName, String password, int num) {
        // 配置连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(userName);
        factory.setPassword(password);
        Connection connection = null;
        Channel channel = null;
        try {
            // 建立TCP连接
            connection = factory.newConnection();
            // 在TCP连接的基础上创建通道
            channel = connection.createChannel();
            // 声明一个队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "NoPublisherConfirmSend!" + System.currentTimeMillis();
            // 发送消息
            while(num-- > 0) {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
                System.out.println(" [NoPublisherConfirmSend] Sent '" + message + "'");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                channel.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}

