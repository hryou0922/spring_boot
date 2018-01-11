package com.hry.spring.rabbitmq.basic.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RoutingSend {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void execute(String host, String userName, String password, String routingKey) {

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

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            String severity = routingKey;
            String message = "RoutingSend-" + System.currentTimeMillis();

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" [RoutingSend] Sent '" + severity + "':'" + message + "'");
        }catch (Exception e){
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

