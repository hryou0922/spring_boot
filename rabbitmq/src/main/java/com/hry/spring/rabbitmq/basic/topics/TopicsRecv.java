package com.hry.spring.rabbitmq.basic.topics;

import com.rabbitmq.client.*;

import java.io.IOException;

public class TopicsRecv {
    private static final String EXCHANGE_NAME = "topic_logs";

    public static void execute(String host, String userName, String password, String[] bindingKeys){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        // 需要在管理后台增加一个hry帐号
        factory.setUsername(userName);
        factory.setPassword(password);

        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            String queueName = channel.queueDeclare().getQueue();

            for (String bindingKey : bindingKeys) {
                channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            }

            System.out.println(" [TopicsRecv] Waiting for messages. To exit press CTRL+C");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [TopicsRecv] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
                }
            };
            channel.basicConsume(queueName, true, consumer);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                // 空值判断，为了代码简洁略
                channel.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
