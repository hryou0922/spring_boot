package com.hry.spring.mvc.aop.log.annotation;

import java.lang.annotation.*;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD, ElementType.TYPE})
public @interface LogEvent {
    ModuleType module() default ModuleType.DEFAULT; // 日志所属的模块
    EventType event() default EventType.DEFAULT; // 日志事件类型
    String desc() default  ""; // 描述信息
}
