package com.hry.spring.mvc.aop.log.aop;

import com.hry.spring.mvc.aop.log.annotation.LogEnable;
import com.hry.spring.mvc.aop.log.annotation.LogModule;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Component
@Aspect
public class LogAspect {
    @Autowired
    private LoggerAspect logAspect;

    @Pointcut("execution(* com.hry.spring.mvc.aop.log.service.*.*(..))")
    public void managerLogPoint() {
    }


    @Around("managerLogPoint()")
    public Object aroundManagerLogPoint(ProceedingJoinPoint jp){

                System.out.println("=======");
        System.out.println("目标方法名为:" + jp.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        jp.getSignature().getDeclaringType().getSimpleName());
        // jp.getSignature().getDeclaringType()： 调用类的类型，通常为接口
        System.out.println("目标方法所属类的类名:" + jp.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(jp.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = jp.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
        }
        //  jp.getTarget() 实际类，通常为jp.getSignature().getDeclaringType()的实现类
        System.out.println("被代理的对象:" + jp.getTarget());
        System.out.println("代理对象自己:" + jp.getThis());
        System.out.println("=======111");

        for (Method method : jp.getSignature().getDeclaringType().getMethods()) {
            System.out.println("==:" + method);
            System.out.println("getAnnotations ==:" + Arrays.toString(method.getAnnotations()));
        }


        LogModule module = null;
        System.out.println(jp.getSignature().getDeclaringType());
        Class target = jp.getTarget().getClass();
        // 获取LogEnable
        LogEnable logEnable = (LogEnable) target.getAnnotation(LogEnable.class);
        if(logEnable != null && logEnable.logEnable()){

        }else {
            System.out.println(target.getCanonicalName() + " 类上没有注解 logEnable，由未开启");
        }

//        Class[] intf = target.getInterfaces();
//        for (Class item : intf) {
//            if (item.getAnnotation(LogModule.class) != null) {
//                module = (LogModule) item.getAnnotation(LogModule.class);
//                break;
//            }
//        }

        return null;
    }
}
