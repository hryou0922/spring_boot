package com.hry.spring.rabbitmq.advanced.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 不增加作保机制保证发送的消息正常到达Broken
 */
public class ReturnListenerSend {
    private final static String EXCHANGE_NAME = "consumer-exchange-test";

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
            connection = factory.newConnection();
            // 在TCP连接的基础上创建通道
            channel = connection.createChannel();
            // 增加回调监听，消息里的属性mandatory=true，且消息没有被broker路由到队列，则此linster才被调用
            channel.addReturnListener((replyCode, replyText, exchange, routingKey1, properties, body) -> {
                System.out.println("[ConsumerSend] replyCode = " + replyCode + " replyText=" + replyText + " " +
                        " exchange = " + exchange + "routingKey=" + routingKey1 + " properties = " + properties +
                        " body = " + new String(body));
            });
            // 声明一个direct交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            String message = "ConsumerSend!" + System.currentTimeMillis();
            // 发送消息
            while(num-- > 0) {
                // 发送一个持久化消息到特定的交换机，设置mandatory=true
                channel.basicPublish(EXCHANGE_NAME, routingKey, true, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println(" [ConsumerSend] Sent '" + message + "'");
            }

            Thread.sleep(2 * 1000);
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

