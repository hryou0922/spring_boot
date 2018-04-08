package com.hry.spring.mvc.aop.log.annotation;

import java.lang.annotation.*;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogKey {
     public abstract boolean isLog();
}
