package com.hry.spring.rabbitmq.advanced.publisherconfirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 通过简单的confirm模式保证生产者消息正常到达broken，
 *  a. 每发送N条消息后，调用waitForConfirms()方法，等待服务器端confirm
 *
 * Created by huangrongyou@yixin.im on 2018/1/29.
 */
public class SimpleConfirmSend {
    private final static String QUEUE_NAME = "publisherconfirm";

    public static void execute(String host, String userName, String password,int num) {
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
            // 声明一个持久化队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "SimpleConfirmSend!" + System.currentTimeMillis();

            // 开启confirm模式：
            channel.confirmSelect();
            // 发送消息
            while(num-- > 0) {
                channel.basicPublish("", QUEUE_NAME, MessageProperties.TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println(" [SimpleConfirmSend] Sent '" + message + "'");
            }
            // 批量等待确认
            channel.waitForConfirms();
        }catch (Exception e){
            e.printStackTrace();
            /**
             *  实际应用中，需要在这是添加发送消息失败的处理逻辑：如重发等等
             *  在以上的模式中，如果发送N条消息，如果有一条失败，则所有的消息都需要重新推送
             */
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
