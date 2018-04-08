package com.hry.spring.mvc.aop.log.service;

import com.hry.spring.mvc.aop.log.annotation.LogEvent2;
import com.hry.spring.mvc.aop.log.annotation.EventType;
import com.hry.spring.mvc.aop.log.manager.model.LogAdmModel;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
public interface BaseManager<Pojo, Bean>{
    void insertLog(LogAdmModel paramLogAdmBean);
  //  @LogEvent2(event= EventType.DELETESINGLE)
    void deleteById(Long paramLong);
    @LogEvent2(event=EventType.ADD)
    Long save(Bean paramBean, Class<Pojo> paramClass);
    @LogEvent2(event=EventType.UPDATE)
    void update(Bean paramBean, Class<Pojo> paramClass);
}
