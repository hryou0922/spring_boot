package com.hry.spring.rabbitmq.advanced.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeoutException;

/**
 * 不增加作保机制保证发送的消息正常到达Broken
 */
public class ConsumerSend {
    private final static String EXCHANGE_NAME = "consumer-exchange";

    public static void execute(String host, String userName, String password, String routingKey, int num) {
        // 配置连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(userName);
        factory.setPassword(password);
        Connection connection = null;
        Channel channel = null;
        try {
            // 建立TCP连接
//            connection = factory.newConnection(Executors.newSingleThreadExecutor(new ThreadFactory() {
//                @Override
//                public Thread newThread(Runnable r) {
//                    return new Thread("my-1");
//                }
//            }));
            connection = factory.newConnection();
            // 在TCP连接的基础上创建通道
            channel = connection.createChannel();
            // 声明一个direct交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            String message = "ConsumerSend!" + System.currentTimeMillis();
            // 发送消息
            while(num-- > 0) {
                // 发送一个持久化消息到特定的交换机，设置mandatory=true
                channel.basicPublish(EXCHANGE_NAME, routingKey, true, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println(" [ConsumerSend] Sent '" + message + "'");
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

