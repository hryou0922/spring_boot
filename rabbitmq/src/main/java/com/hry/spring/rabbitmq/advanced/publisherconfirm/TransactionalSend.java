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
            String message = "Transactional!" + System.currentTimeMillis();


            try {
                // 开启事物
                channel.txSelect();
                // 发送消息
                while(num-- > 0) {
                    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
                    System.out.println(" [TransactionalSend] Sent + [" + num + "] '" + message + "'");
                }

                // 不注解下面语句，可以进入channel.txRollback()逻辑
//                if(true){
//                    throw new IOException("test channel.txRollback() ");
//                }
                // 注解掉下面一行可以模拟不进行提交
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
