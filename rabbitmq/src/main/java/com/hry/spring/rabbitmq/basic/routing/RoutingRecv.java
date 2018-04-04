package com.hry.spring.rabbitmq.basic.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;

public class RoutingRecv {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void execute(String host, String userName, String password, String[] colours){
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
            // 声明一个临时队列
            String queueName = channel.queueDeclare().getQueue();
            // 绑定路由，同一个队列可以绑定多个值
            for (String colour : colours) {
                channel.queueBind(queueName, EXCHANGE_NAME, colour);
            }
            System.out.println(" [RoutingRecv-" + Arrays.toString(colours) + "] Waiting for messages.");
            // 定义消息的回调处理类
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [RoutingRecv-" + Arrays.toString(colours) + "] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
                }
            };
            // 接收消息
            channel.basicConsume(queueName, true, consumer);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        }
    }
}
