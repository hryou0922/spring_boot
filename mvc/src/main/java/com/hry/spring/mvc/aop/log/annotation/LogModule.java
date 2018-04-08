package com.hry.spring.mvc.aop.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface LogModule {
     ModuleType module(); // 所属的模块
     Class<?> beanClass();
     Class<?> pojoClass();
}
