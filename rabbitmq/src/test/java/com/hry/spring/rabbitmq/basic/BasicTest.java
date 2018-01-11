package com.hry.spring.rabbitmq.basic;

import com.hry.spring.rabbitmq.basic.helloworld.HelloworldRecv;
import com.hry.spring.rabbitmq.basic.helloworld.HelloworldSend;
import com.hry.spring.rabbitmq.basic.publishsubscribe.Publish;
import com.hry.spring.rabbitmq.basic.publishsubscribe.Subscribe;
import com.hry.spring.rabbitmq.basic.routing.RoutingRecv;
import com.hry.spring.rabbitmq.basic.routing.RoutingSend;
import com.hry.spring.rabbitmq.basic.workqueues.WorkQueuesRecv;
import com.hry.spring.rabbitmq.basic.workqueues.WorkQueuesSend;
import org.junit.Test;

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
    public void routing() throws InterruptedException {
        final String routing = "key-routing";
        final String[] severitys = new String[2];
        severitys[0] = routing;
        severitys[1] = routing + "-1";

        // 接收端
        int recNum = 2;
        while(recNum-- > 0) {
            final int recId = recNum;
            executorService.submit(() -> {
                RoutingRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, severitys);
            });
        }
        Thread.sleep(5* 100);
        // 发送端
        int sendNum = 2;
        while(sendNum-- > 0){
            executorService.submit(() -> {
                RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
            });
        }

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

}
