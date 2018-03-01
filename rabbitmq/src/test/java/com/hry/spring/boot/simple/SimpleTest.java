package com.hry.spring.boot.simple;

import com.hry.spring.rabbitmq.boot.simple1.SpringBootRabbitApplication1;
import com.hry.spring.rabbitmq.boot.simple1.ReceiveMsg1;
import com.hry.spring.rabbitmq.boot.simple1.SendMsg1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by huangrongyou@yixin.im on 2018/2/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringBootRabbitApplication1.class, value = "spring.profiles.active=boot")
public class SimpleTest {


    @Autowired
    private ReceiveMsg1 receiveMsg1;
    @Autowired
    private SendMsg1 sendMsg1;

    @Test
    public void sendAndReceive_1(){
        String testContent = "send msg via spring boot - 1";
        sendMsg1.send_1(testContent);
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
