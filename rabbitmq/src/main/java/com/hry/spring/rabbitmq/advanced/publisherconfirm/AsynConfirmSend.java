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
public class AsynConfirmSend {
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

            // 添加回调对象，处理返回值
            channel.addConfirmListener(new ConfirmListener(){
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("[AsynConfirmSend] handleAck : deliveryTag = " + deliveryTag + " multiple = " + multiple);
                }
                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    System.out.println("[AsynConfirmSend] handleNack : deliveryTag = " + deliveryTag + " multiple = " + multiple);
                }
            });

            // 开启confirm模式：
            channel.confirmSelect();
            // 发送消息
            while(num-- > 0) {
                // 消息属性持久化
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println(" [TransactionalSend] Sent '" + message + "'");
            }

            Thread.sleep(10 * 1000);
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
