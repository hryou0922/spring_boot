package com.hry.spring.rabbitmq.basic;

import com.hry.spring.rabbitmq.basic.helloworld.HelloworldRecv;
import com.hry.spring.rabbitmq.basic.helloworld.HelloworldSend;
import com.hry.spring.rabbitmq.basic.publishsubscribe.Publish;
import com.hry.spring.rabbitmq.basic.publishsubscribe.Subscribe;
import com.hry.spring.rabbitmq.basic.routing.RoutingRecv;
import com.hry.spring.rabbitmq.basic.routing.RoutingSend;
import com.hry.spring.rabbitmq.basic.rpc.RpcClient;
import com.hry.spring.rabbitmq.basic.rpc.RpcServer;
import com.hry.spring.rabbitmq.basic.topics.TopicsRecv;
import com.hry.spring.rabbitmq.basic.topics.TopicsSend;
import com.hry.spring.rabbitmq.basic.workqueues.WorkQueuesRecv;
import com.hry.spring.rabbitmq.basic.workqueues.WorkQueuesSend;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangrongyou@yixin.im on 2018/1/10.
 */
public class BasicTest {
    // 测试线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    // rabbitmq的IP地址
    private final String rabbitmq_host = "10.240.80.147";
    // rabbitmq的用户名称
    private final String rabbitmq_user = "hry";
    // rabbitmq的用户密码
    private final String rabbitmq_pwd = "hry";

    @Test
    public void helloworld() throws InterruptedException {
        // 接收端
        executorService.submit(() -> {
            HelloworldRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
        });
        Thread.sleep(5* 100);

        // 发送端
        executorService.submit(() -> {
            HelloworldSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
        });
        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void workqueues() throws InterruptedException {
        // 接收端
        int recNum = 2;
        while(recNum-- > 0) {
            final int recId = recNum;
            executorService.submit(() -> {
                WorkQueuesRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, recId);
            });
        }
        Thread.sleep(5* 100);
        // 发送端
        int sendNum = 4;
        while(sendNum-- > 0){
            executorService.submit(() -> {
                WorkQueuesSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
            });
        }

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void publishsubscribe() throws InterruptedException {
        // 接收端
        int recNum = 2;
        while(recNum-- > 0) {
            final int recId = recNum;
            executorService.submit(() -> {
                Subscribe.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, recId);
            });
        }
        Thread.sleep(5* 100);
        // 发送端
        int sendNum = 2;
        while(sendNum-- > 0){
            executorService.submit(() -> {
                Publish.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
            });
        }

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void routing_1() throws InterruptedException {
        // 接收端 1：绑定 orange 值
        executorService.submit(() -> {
            String[] colours = {"orange"};
            RoutingRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, colours);
        });
        // 接收端 2：绑定 black、green 值
        executorService.submit(() -> {
            String[] colours = {"black","green"};
            RoutingRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, colours);
        });

        Thread.sleep(5* 100);
        // 发送端 ： 发送 black，只有接收端1收到
        executorService.submit(() -> {
            String routing = "orange";
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // 发送端 ： 发送 green、black，只有接收端2收到
        executorService.submit(() -> {
            String routing = "green";
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void routing_2() throws InterruptedException {

        // 接收端：同时创建两个接收端，同时绑定black
        int recNum = 2;
        while(recNum-- > 0) {
             // 接收端：绑定 black 值
            executorService.submit(() -> {
                String[] colours = {"black"};
                RoutingRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, colours);
            });
        }

        Thread.sleep(5* 100);
        // 发送端1 ： 发送 black，所有的接收端都会收到
        executorService.submit(() -> {
            String routing = "black";
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // 发送端2 ： 发送 green，所有的接收端都不会收到
        executorService.submit(() -> {
            String routing = "green";
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void topics() throws InterruptedException {

        // 消费者1：绑定 *.orange.* 值
        executorService.submit(() -> {
            String[] bindingKeys = {"*.orange.*"};
            TopicsRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, bindingKeys);
        });

        // 消费者2：绑定  "*.*.rabbit" 和 "lazy.#"值
        executorService.submit(() -> {
            String[] bindingKeys = {"*.*.rabbit", "lazy.#"};
            TopicsRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, bindingKeys);
        });

        Thread.sleep(5* 100);
        // 生产者1 ： 发送 black，所有的接收端都会收到
        executorService.submit(() -> {
            String routing = "quick.orange.rabbit";
            TopicsSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // 生产者2 ： 发送 green，所有的接收端都不会收到
        executorService.submit(() -> {
            String routing = "lazy.pink.rabbit";
            TopicsSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    public void rpc() throws InterruptedException {

        // rpc服务端
        executorService.submit(() -> {
            RpcServer.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
        });

        // rpc客户端
        executorService.submit(() -> {
            RpcClient.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, "rpc test");
        });

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

}
