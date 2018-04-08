package com.hry.spring.mvc.aop.log.service;

import com.hry.spring.mvc.aop.log.annotation.LogEvent2;
import com.hry.spring.mvc.aop.log.annotation.LogModule;
import com.hry.spring.mvc.aop.log.annotation.EventType;
import com.hry.spring.mvc.aop.log.annotation.ModuleType;
import com.hry.spring.mvc.aop.log.model.AlarmType;
import com.hry.spring.mvc.aop.log.model.AlarmTypeBean;


/**
 * 报警管理类，真正要截获的类
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
//@LogModule(module= ModuleType.ALARMTYPE, beanClass=AlarmTypeBean.class, pojoClass=AlarmType.class)
public interface AlarmTypeManager extends BaseManager<AlarmType, AlarmTypeBean>
{
     @LogEvent2(event= EventType.UPDATE)
     void updateAll(String paramString1, String paramString2);
     @LogEvent2(event=EventType.ADD)
     void saveAlarmType(AlarmTypeBean paramAlarmTypeBean);
     @LogEvent2(event=EventType.UPDATE)
     void updateAlarmType(AlarmTypeBean paramAlarmTypeBean);
}
