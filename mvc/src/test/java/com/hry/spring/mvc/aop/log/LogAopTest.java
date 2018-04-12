package com.hry.spring.mvc.aop.log;

import com.hry.spring.mvc.aop.log.service.IStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=LogAopApplication.class)
public class LogAopTest {
    @Autowired
    private IStudentService studentService;

    @Test
    public void studentService(){
        studentService.deleteById("1","s");
    }

}
