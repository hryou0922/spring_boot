package com.hry.spring.rabbitmq.advanced.publisherconfirm;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 通过AMQP的事物保证生产者消息正常到达broken
 * Created by huangrongyou@yixin.im on 2018/1/29.
 */
public class TransactionalSend {
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
            String message = "TransactionalSend!" + System.currentTimeMillis();

            try {
                // 开启事物
                channel.txSelect();
                // 发送消息
                while(num-- > 0) {
                    // 发送一个持久化消息到特定的交换机
                    channel.basicPublish(EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                    System.out.println(" [TransactionalSend] Sent + [" + num + "] '" + message + "'");
                }

                // 不注解下面语句，可以进入channel.txRollback()逻辑
//                if(true){
//                    throw new IOException("consumer channel.txRollback() ");
//                }
                // 提交事物
                channel.txCommit();
            }catch(IOException e){
                e.printStackTrace();
                // 回滚事物
                channel.txRollback();
            }
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
