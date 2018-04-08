package com.hry.spring.mvc.aop.log.aop;

import com.hry.spring.mvc.aop.log.manager.model.LogAdmModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 对特定方法进行截获，任何一步不成功都要求结束
 * <p>
 * a aop截获特定包下的方法
 * b 判断此类是否被LogModule注解
 * c 寻找JoinPoint点的方法，是否被LogEvent注解
 * d 生成LogAdmBean主要值(日志信息类)，调用方法，解析JoinPoint，根据LogEvent、LogModule往LogAdmBean写值
 * <p>
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
// @Component
//@Aspect
@Deprecated
public class AopAspect {
    @Autowired
    private LoggerAspect logAspect;

    @Pointcut("execution(* com.hry.spring.mvc.aop.log.service.*.*(..))")
    public void managerLogPoint() {
    }


    @Around("managerLogPoint()")
    public Object aroundManagerLogPoint(ProceedingJoinPoint jp)
            throws Throwable {

//        System.out.println("=======");
//        System.out.println("目标方法名为:" + jp.getSignature().getName());
//        System.out.println("目标方法所属类的简单类名:" +        jp.getSignature().getDeclaringType().getSimpleName());
//        System.out.println("目标方法所属类的类名:" + jp.getSignature().getDeclaringTypeName());
//        System.out.println("目标方法声明类型:" + Modifier.toString(jp.getSignature().getModifiers()));
//        //获取传入目标方法的参数
//        Object[] args = jp.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
//        }
//        System.out.println("被代理的对象:" + jp.getTarget());
//        System.out.println("代理对象自己:" + jp.getThis());
//        System.out.println("=======111");

        // 获取有LogModule修饰的类
        String optModel = CommonMethod.convertModule(jp);
        if (optModel == "-1") {
            return jp.proceed();
        }
        Object returnObj = null;
        // 获取事件
        String optEvent = this.logAspect.convertEvent(jp);
        if (optEvent != "-1") {
            LogAdmModel logBean = new LogAdmModel();
            logBean.setAdmModel(optModel);
            logBean.setAdmEvent(optEvent);
            logBean.setCreateDate(new Date());
            boolean isSuccess = this.logAspect.processingManagerLogMessage(jp,
                    logBean);
            returnObj = jp.proceed();
            if (isSuccess) {
                this.logAspect.saveLog(jp, logBean);
            }
        } else {
            returnObj = jp.proceed();
        }
        return returnObj;
    }
}
