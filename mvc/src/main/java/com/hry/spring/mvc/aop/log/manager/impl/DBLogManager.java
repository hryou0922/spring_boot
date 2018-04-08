package com.hry.spring.mvc.aop.log.manager.impl;

import com.alibaba.fastjson.JSON;
import com.hry.spring.mvc.aop.log.manager.model.LogAdmModel;
import com.hry.spring.mvc.aop.log.manager.ILogManager;
import org.springframework.stereotype.Service;

/**
 * 将日志存入数据库
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Service
public class DBLogManager implements ILogManager {
    @Override
    public void dealLog(LogAdmModel paramLogAdmBean) {
        System.out.println("将日志存入数据库,日志内容如下: " + JSON.toJSONString(paramLogAdmBean));
    }
}
