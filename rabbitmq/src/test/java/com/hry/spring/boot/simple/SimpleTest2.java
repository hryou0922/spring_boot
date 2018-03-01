package com.hry.spring.boot.simple;

import com.hry.spring.rabbitmq.boot.simple1.SpringBootRabbitApplication1;
import com.hry.spring.rabbitmq.boot.simple2.ReceiveMsg2;
import com.hry.spring.rabbitmq.boot.simple2.SendMsg2;
import com.hry.spring.rabbitmq.boot.simple2.SpringBootRabbitApplication2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by huangrongyou@yixin.im on 2018/2/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringBootRabbitApplication2.class, value = "spring.profiles.active=boot")
public class SimpleTest2 {

    @Autowired
    private ReceiveMsg2 receiveMsg2;

    @Autowired
    private SendMsg2 sendMsg2;

    @Test
    public void sendAndReceive_2(){
        String testContent = "send msg via spring boot -2";
        sendMsg2.send_2(testContent);
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
