package com.hry.spring.rabbitmq.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by huangrongyou@yixin.im on 2018/1/9.
 */
public class RabiitHelloWorld {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.240.80.147");
        // 需要在管理后台增加一个hry帐号
        factory.setUsername("hry");
        factory.setPassword("hry");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        int num = 10000;
        while(num-- > 0){
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        //    System.out.println(" [x] Sent '" + message + "'");
        }

        channel.close();
        connection.close();
    }
}
