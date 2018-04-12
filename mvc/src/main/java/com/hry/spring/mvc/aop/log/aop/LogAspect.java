package com.hry.spring.mvc.aop.log.aop;

import com.hry.spring.mvc.aop.log.annotation.*;
import com.hry.spring.mvc.aop.log.manager.ILogManager;
import com.hry.spring.mvc.aop.log.manager.model.LogAdmModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Component
@Aspect
public class LogAspect {
    @Autowired
    private LogInfoGeneration logInfoGeneration;

    @Autowired
    private ILogManager logManager;

    @Pointcut("execution(* com.hry.spring.mvc.aop.log.service..*.*(..))")
    public void managerLogPoint() {
    }

    @Around("managerLogPoint()")
    public Object aroundManagerLogPoint(ProceedingJoinPoint jp) throws Throwable {

        printJoinPoint(jp);
        Class target = jp.getTarget().getClass();
        // 获取LogEnable
        LogEnable logEnable = (LogEnable) target.getAnnotation(LogEnable.class);
        if(logEnable == null || !logEnable.logEnable()){
            return jp.proceed();
        }

        // 获取类上的LogEvent做为默认值
        LogEvent logEventClass = (LogEvent) target.getAnnotation(LogEvent.class);
        Method method = getInvokedMethod(jp);
        if(method == null){
            return jp.proceed();
        }

        // 获取方法上的LogEvent
        LogEvent logEventMethod = method.getAnnotation(LogEvent.class);
        if(logEventMethod == null){
            return jp.proceed();
        }

        String optEvent = logEventMethod.event().getEvent();
        String optModel = logEventMethod.module().getModule();
        String desc = logEventMethod.desc();

        if(logEventClass != null){
            // 如果方法上的值为默认值，则使用全局的值进行替换
            optEvent = optEvent.equals(EventType.DEFAULT) ? logEventClass.event().getEvent() : optEvent;
            optModel = optModel.equals(ModuleType.DEFAULT) ? logEventClass.module().getModule() : optModel;
        }

        LogAdmModel logBean = new LogAdmModel();
        logBean.setAdmModel(optModel);
        logBean.setAdmEvent(optEvent);
        logBean.setDesc(desc);
        logBean.setCreateDate(new Date());
        logInfoGeneration.processingManagerLogMessage(jp,
                logBean, method);
        Object returnObj = jp.proceed();

        if(optEvent.equals(EventType.LOGIN)){
            //TODO 如果是登录，还需要根据返回值进行判断是不是成功了，如果成功了，则执行添加日志。这里判断比较简单
            if(returnObj != null) {
                this.logManager.dealLog(logBean);
            }
        }else {
            this.logManager.dealLog(logBean);
        }
        return returnObj;
    }

    /**
     * 打印节点信息
     * @param jp
     */
    private void printJoinPoint(ProceedingJoinPoint jp) {
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
    }

    /**
     * 获取请求方法
     *
     * @param jp
     * @return
     */
    public Method getInvokedMethod(JoinPoint jp) {
        // 调用方法的参数
        List classList = new ArrayList();
        for (Object obj : jp.getArgs()) {
            classList.add(obj.getClass());
//            if (obj instanceof ArrayList)
//                classList.add(List.class);
//            else if (obj instanceof LinkedList)
//                classList.add(List.class);
//            else if (obj instanceof HashMap)
//                classList.add(Map.class);
//            else if (obj instanceof HashSet)
//                classList.add(Set.class);
//            else if (obj == null)
//                classList.add(null);
//            else {
//                classList.add(obj.getClass());
//            }
        }
        Class[] argsCls = (Class[]) classList.toArray(new Class[0]);

        // 被调用方法名称
        String methodName = jp.getSignature().getName();
        Method method = null;
        try {
            method = jp.getTarget().getClass().getMethod(methodName, argsCls);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }
}
