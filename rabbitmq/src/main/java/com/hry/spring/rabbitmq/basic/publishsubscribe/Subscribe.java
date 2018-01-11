package com.hry.spring.rabbitmq.basic.publishsubscribe;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Subscribe {
    private static final String EXCHANGE_NAME = "logs";

    public static void execute(String host, String userName, String password, int id){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        // 需要在管理后台增加一个hry帐号
        factory.setUsername(userName);
        factory.setPassword(password);
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            System.out.println(" [Subscribe-"+id+"] Waiting for messages.");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [Subscribe-"+id+"] Received '" + message + "'");
                }
            };
            channel.basicConsume(queueName, true, consumer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
