package com.hry.spring.rabbitmq.advanced.publisherconfirm;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 通过简单的confirm模式保证生产者消息正常到达broken，
 *  a. 每发送N条消息后，调用waitForConfirms()方法，等待服务器端confirm
 *
 * Created by huangrongyou@yixin.im on 2018/1/29.
 */
public class SimpleConfirmSend {
    private final static String EXCHANGE_NAME = "publisherconfirm-exchange";

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

            // 开启confirm模式：
            channel.confirmSelect();

            // ======== 单个确认模式 begin ======
            // 发送消息
//            while(num-- > 0) {
//                // 发送一个持久化消息到特定的交换机
//                channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
//                System.out.println(" [ConsumerConfirmSend] Sent '" + message + "'");
//                // 等待服务端返回Basic.Ack后，才执行下一个循环
//                if(!channel.waitForConfirms()){
//                    System.out.println("message haven't arrived broker");
                      // 在这里可以对发送失败的记录进行处理：如重发
//                }
//            }
            // 批量等待确认: 返回true: 如果所有的消息都收到有确认应答，没有消息被拒绝

            // ======== 单个确认模式 begin ======

            // ======== 批量确认模式 end ======
            // 发送消息
            while(num-- > 0) {
                // 发送一个持久化消息到特定的交换机
                channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println(" [ConsumerConfirmSend] Sent '" + message + "'");
            }
            // 批量等待确认: 返回true: 如果所有的消息都收到有确认应答，没有消息被拒绝
            if(!channel.waitForConfirms()){
                System.out.println("Not all message have arrived broker");
                // 实际应用中，需要在这是添加发送消息失败的处理逻辑：如重发等等
                // 在这种的模式中，如果发送N条消息，如果有一条失败，则所有的消息都需要重新推送
            }
            // ======== 批量确认模式 end ======



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
