package com.hry.spring.rabbitmq.advanced.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumerRecv {
    private final static String QUEUE_NAME = "test";
    private final static String EXCHANGE_NAME = "consumer-exchange";

    public static void execute(String host, String userName, String password, String routingKey){
        // 配置连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        // 需要在管理后台增加一个hry帐号
        factory.setUsername(userName);
        factory.setPassword(password);

        Connection connection = null;

        try {
            // 定义处理返回消息的线程池，如果消费端收到消息比较多，则可以考虑增加自定义线程池
            ExecutorService es = Executors.newFixedThreadPool(20, new  ThreadFactory(){
                private AtomicInteger threadNumber = new AtomicInteger(1);

                public Thread newThread(Runnable r) {
                    SecurityManager s = System.getSecurityManager();
                    ThreadGroup group = (s != null) ? s.getThreadGroup() :
                            Thread.currentThread().getThreadGroup();
                    String namePrefix = "my-consumer-thread-";
                    Thread t = new Thread(group, r,
                            namePrefix + threadNumber.getAndIncrement(),
                            0);
                    if (t.isDaemon())
                        t.setDaemon(false);
                    if (t.getPriority() != Thread.NORM_PRIORITY)
                        t.setPriority(Thread.NORM_PRIORITY);
                    return t;
                }
            });
            // 建立TCP连接
            connection = factory.newConnection(es);
      //      connection = factory.newConnection();
            // 在TCP连接的基础上创建通道
            final Channel  channel = connection.createChannel();
            // 声明一个direct交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            // 声明一个持久化队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            // 绑定路由，同一个队列可以绑定多个值
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, routingKey);
            System.out.println("[ConsumerRecv] Waiting for messages.");
            // 默认消费者实现
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    // Called when a <code><b>basic.deliver</b></code> is received for this consumer.
                    // 即收到消息时，执行此操作
                    String message = new String(body, "UTF-8");
                    System.out.println("[ConsumerRecv] Received '" + message + "'");
                    System.out.println("handleDelivery Running thread " + Thread.currentThread().getName());
                    // channel.basicCancel(consumerTag);
                }

                @Override
                public void handleConsumeOk(String consumerTag) {
                    // Called when the consumer is registered by a call to any of the* {@link Channel#basicConsume} methods
                    // 在执行Channel#basicConsume时，此方法就被触发
                    super.handleConsumeOk(consumerTag);
                    System.out.println("[ConsumerRecv] handleConsumeOk = " + consumerTag);
                    System.out.println("handleConsumeOk Running thread " + Thread.currentThread().getName());
                }

                @Override
                public void handleCancelOk(String consumerTag) {
                    // Called when the consumer is cancelled by a call to {@link Channel#basicCancel}
                    // 当执行 Channel#basicCancel方法拒绝消息时，此方法被调用
                    super.handleCancelOk(consumerTag);
                    System.out.println("[ConsumerRecv] handleCancelOk = " + consumerTag);
                }

                @Override
                public void handleCancel(String consumerTag) throws IOException {
                    // 除了执行Channel#basicCancel方法拒绝消息的情况，此方法被调用
                    super.handleCancel(consumerTag);
                    System.out.println("[ConsumerRecv] handleCancel = " + consumerTag);
                }

                @Override
                public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
                    // 当channels和connections被关闭时，被调用
                    super.handleShutdownSignal(consumerTag, sig);
                    System.out.println("[ConsumerRecv] handleShutdownSignal = " + consumerTag);
                }


                @Override
                public void handleRecoverOk(String consumerTag) {
                    /**
                     * Called when a <code><b>basic.recover-ok</b></code> is received
                     * in reply to a <code><b>basic.recover</b></code>. All messages
                     * received before this is invoked that haven't been <i>ack</i>'ed will be
                     * re-delivered. All messages received afterwards won't be.
                     */
                    super.handleRecoverOk(consumerTag);
                    System.out.println("[ConsumerRecv] handleRecoverOk = " + consumerTag);
                }
            };
            // 接收消息
            channel.basicConsume(QUEUE_NAME, true, consumer);

            Thread.sleep(5 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 不能执行关闭，如果关闭链接，则后续消息无法收到。无法模拟后续接收消息的情况
//            try {
//             //   channel.close();
//                connection.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
