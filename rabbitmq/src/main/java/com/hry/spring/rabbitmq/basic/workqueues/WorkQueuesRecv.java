package com.hry.spring.rabbitmq.basic.workqueues;

import com.rabbitmq.client.*;

import java.io.IOException;
/**
 * Created by huangrongyou@yixin.im on 2018/1/9.
 */
public class WorkQueuesRecv {
    private static final String TASK_QUEUE_NAME = "task_queue";


    public static void execute(String host, String userName, String password, int id){
        // 配置连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        // 需要在管理后台增加一个hry帐号
        factory.setUsername(userName);
        factory.setPassword(password);
        try {
            // 建立TCP连接
            final Connection connection = factory.newConnection();
            // 在TCP连接的基础上创建通道
            final Channel channel = connection.createChannel();
            // 声明一个队列
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            System.out.println(" [WorkQueuesRecv-" +id+ "] Waiting for messages.");
            // 每个客户端每次最后获取N个消息
        //    channel.basicQos(1);
            // 默认消费者实现
            final Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");

                    System.out.println(" [WorkQueuesRecv-" +id+ "] Received '" + message + "'");
                    try {
                        doWork(message);
                    } finally {
                        System.out.println(" [WorkQueuesRecv-" +id+ "] Done");

                        // 情况一：对处理好的消息进行应答
                        channel.basicAck(envelope.getDeliveryTag(), false);

                        // 情况二：对于id=0的消费者者正常应答消息，其它id=0，解决此消息并要求重发
//                        if(id == 0){
//                            // 对处理好的消息进行应答
//                            channel.basicAck(envelope.getDeliveryTag(), false);
//                        }else {
                            // 拒绝当前这条消息
                  //          channel.basicReject(envelope.getDeliveryTag(), true);
                            // 拒绝包含本条delivery_tag 所对应消息在内的所有比该值小的消息都被拒绝了（除了已经被 ack 的以外)
                            // channel.basicNack(envelope.getDeliveryTag(), false, false);
//                        }


                    }
                }
            };
            // 获取消息
            channel.basicConsume(TASK_QUEUE_NAME, false, consumer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void doWork(String task) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
