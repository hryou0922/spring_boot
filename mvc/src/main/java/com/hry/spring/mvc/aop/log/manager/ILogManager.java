package com.hry.spring.mvc.aop.log.manager;

import com.hry.spring.mvc.aop.log.manager.model.LogAdmModel;

/**
 * 日志处理模块
 *  1. 可以将日志存入数据
 *  2. 可以将日志发送到开中间件，如果redis, mq等等
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
public interface ILogManager {
    /**
     * 日志处理模块
     * @param paramLogAdmBean
     */
    void dealLog(LogAdmModel paramLogAdmBean);
}
