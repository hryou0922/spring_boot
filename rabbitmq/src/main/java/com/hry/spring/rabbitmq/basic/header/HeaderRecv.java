package com.hry.spring.rabbitmq.basic.header;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HeaderRecv {
    private static final String EXCHANGE_NAME = "header-exchange";

    public static void execute(String host, String userName, String password, final Map<String,Object> myHeaders){
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
            // 声明一个headers交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.HEADERS);

            // 声明一个临时队列
            String queueName = channel.queueDeclare().getQueue();
            // 将队列绑定到指定交换机上
            channel.queueBind(queueName, EXCHANGE_NAME, "", myHeaders);

            System.out.println(" [HeaderRecv ["+ myHeaders +"]] Waiting for messages.");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [HeaderRecv ["+ myHeaders +"] ] Received '" + properties.getHeaders() + "':'" + message + "'");
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
