package com.hry.spring.mvc.aop.log.annotation;

import java.lang.annotation.*;

/**
 * 日志开关量
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LogEnable {
    /**
     * 如果为true，则类下面的LogEvent启作用，否则忽略
     * @return
     */
    boolean logEnable() default true;
}
