package com.hry.spring.rabbitmq.advanced.consumerconfirm;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 通过简单的confirm模式保证生产者消息正常到达broken，
 *  a. 每发送N条消息后，调用waitForConfirms()方法，等待服务器端confirm
 *
 * Created by huangrongyou@yixin.im on 2018/1/29.
 */
public class ConsumerConfirmSend {
    private final static String EXCHANGE_NAME = "consumerconfirm-exchange";

    public static void execute(String host, String userName, String password,String routingKey, int num) {
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
            // 声明一个direct交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            String message = "ConsumerConfirmSend!" + System.currentTimeMillis();

            // 发送一个持久化消息到特定的交换机
            channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            System.out.println(" [ConsumerConfirmSend] Sent '" + message + "'");
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
