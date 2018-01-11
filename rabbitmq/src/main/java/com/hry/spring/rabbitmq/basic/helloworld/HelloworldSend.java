package com.hry.spring.rabbitmq.basic.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class HelloworldSend {
        private final static String QUEUE_NAME = "hello";

    public static void execute(String host, String userName, String password) {
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
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!" + System.currentTimeMillis();
            // 发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [HelloworldSend] Sent '" + message + "'");
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

