package com.hry.spring.rabbitmq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangrongyou@yixin.im on 2018/1/29.
 */
public class BaseTest {
    // 测试线程池
    protected ExecutorService executorService = Executors.newFixedThreadPool(10);

    // rabbitmq的IP地址
    protected final String rabbitmq_host = "10.240.80.134";
    // rabbitmq的用户名称
    protected final String rabbitmq_user = "hry";
    // rabbitmq的用户密码
    protected final String rabbitmq_pwd = "hry";
}
