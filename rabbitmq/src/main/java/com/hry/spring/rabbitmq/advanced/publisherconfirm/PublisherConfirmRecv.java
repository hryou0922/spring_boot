package com.hry.spring.rabbitmq.advanced.publisherconfirm;

import com.rabbitmq.client.*;

import java.io.IOException;

public class PublisherConfirmRecv {
    private final static String QUEUE_NAME = "publisherconfirm";
    private final static String EXCHANGE_NAME = "publisherconfirm-exchange";

    public static void execute(String host, String userName, String password, String routingKey){
        // 配置连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        // 需要在管理后台增加一个hry帐号
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
            // 声明一个持久化队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            // 绑定路由，同一个队列可以绑定多个值
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, routingKey);
            System.out.println(" [ConsumerRecv] Waiting for messages.");
            // 默认消费者实现
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [ConsumerRecv] Received '" + message + "'");
                }
            };
            // 接收消息
            channel.basicConsume(QUEUE_NAME, true, consumer);
            // 暂停5s
            Thread.sleep(5 * 1000);

            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 不能执行关闭，如果关闭链接，则后续消息无法收到。无法模拟后续接收消息的情况
            try {
             connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
