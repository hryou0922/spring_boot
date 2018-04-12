package com.hry.spring.mvc.aop.log.annotation;

import java.lang.annotation.*;

/**
 * 此注解可以注解到成员变量，也可以注解到方法的参数上
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogKey {
     String keyName() default ""; // key的名称
     boolean isUserId() default false; // 此字段是否是本次操作的userId，这里略
     boolean isLog() default true; // 是否加入到日志中
}
