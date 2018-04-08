package com.hry.spring.mvc.aop.log.aop;

import com.hry.spring.mvc.aop.log.annotation.LogEvent2;
import com.hry.spring.mvc.aop.log.annotation.LogKey;
import com.hry.spring.mvc.aop.log.annotation.EventType;
import com.hry.spring.mvc.aop.log.manager.model.LogAdmModel;
import com.hry.spring.mvc.aop.log.service.BaseManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Service
public class LoggerAspect {

    public void saveLog(ProceedingJoinPoint jp, LogAdmModel logBean) {
        try {
            ((BaseManager) jp.getTarget()).insertLog(logBean);
            //    System.out.println(" ===== ((BaseManager) jp.getTarget()).dealLog(logBean);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean processingManagerLogMessage(ProceedingJoinPoint jp, LogAdmModel logBean) {
        String optEvent = convertEvent(jp);
        boolean isSuccess = true;
        try {
            if (optEvent.equals(EventType.LOGIN)) {
                // 登陆
                Integer result = (Integer) jp.proceed();
                isSuccess = processingLoginUser(jp, logBean, result);
            } else {
                // 管理员操作信息
                processingAdmOpt(jp, logBean, optEvent);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }


    /**
     * 处理用户登陆，
     * 向logBean添加UserId值
     *
     * @param jp
     * @param logBean
     * @param result
     * @return
     */
    public boolean processingLoginUser(ProceedingJoinPoint jp, LogAdmModel logBean, Integer result) {
        boolean isSuccess = result.intValue() == 200;
        return isSuccess;
    }

    public void processingAdmOpt(ProceedingJoinPoint jp, LogAdmModel logBean, String optEvent) {
        String message = "";
        Object obj = jp.getArgs()[0];
        try {
            // EventType.ADD, EventType.UPDATE
            if ((optEvent.equals("2")) || (optEvent.equals("3"))) {
                message = getAddOrUpdateEntitiesMessage(jp, obj);
            } else if (optEvent.equals("4")) {
                // EventType.DELETESINGLE
                message = getEntityMessage(obj);
            } else if (optEvent.equals("5")) {
                // EventType.DELETEALL
                message = getDeleteAllMessage(jp, obj);
            }
            logBean.setAdmEntity(message);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public String getAddOrUpdateEntitiesMessage(JoinPoint jp, Object object)
            throws Throwable {
        if (object == null) {
            return "";
        }
        return "getAddOrUpdateEntitiesMessage";
    }

    public String getDeleteAllMessage(JoinPoint jp, Object obj)
            throws Throwable {
        return "getDeleteAllMessage";
    }

    /**
     * 解析对象，读取被LogKey修饰字段的值
     *
     * @param object
     * @return
     * @throws Throwable
     */
    public String getEntityMessage(Object object)
            throws Throwable {
        if (object == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer("");
        Class clazz = object.getClass();
        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            Annotation[] ans = f.getAnnotations();
            for (Annotation an : ans) {
                if ((an instanceof LogKey) && (((LogKey) an).isLog())) {
                    String fieldName = f.getName();
                    f.setAccessible(true);
                    System.out.println("=============" + f.get(object));
                    String getMethod = new StringBuilder().append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1)).toString();
                    try {
                        Method method = clazz.getMethod(getMethod, new Class[0]);
                        String result = (String) method.invoke(object, new Object[0]);
                        if (null != result) {
                            sb.append(result);
                            sb.append(",");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            }
        }
        String message = sb.toString();
        if (message.endsWith(",")) {
            message = message.substring(0, message.length() - 1);
        }
        return message;
    }

    /**
     * 解析节点，从方法上获取
     *
     * @param jp
     * @return
     */
    public String convertEvent(JoinPoint jp) {
        String event = "-1";
        //      System.out.println(jp.getSignature());
        Class clazz = jp.getTarget().getClass();
        Method method = null;
        Class[] classArray = CommonMethod.toClassArray(jp);
        try {
            method = clazz.getMethod(jp.getSignature().getName(), classArray);
        } catch (NoSuchMethodException e){
            /* BaseManager的方法中，第一个参数约定是泛型类，此时在此参数在字节码是Object.class，而不是真实的类，所有第一个参数需要进行替换
            * update方法
            * 在BaseManager.java中 public void update(Bean paramBean, Class<Pojo> paramClass)
            * 在子类AlarmTypeManagerImpl中是 void com.hry.spring.mvc.aop.log.service.impl.BaseManagerImpl.update(java.lang.Object,java.lang.Class)
            *
             */
            classArray[0] = Object.class;
            try {
                method = clazz.getMethod(jp.getSignature().getName(), classArray);
            } catch (NoSuchMethodException e1) {
                e.printStackTrace();
                return "-1";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
        if (method == null) {
            return "-1";
        }
        LogEvent2 eventAn = method.getAnnotation(LogEvent2.class);
        if (eventAn == null) {
            return "-1";
        }
        event = eventAn.event().getEvent();
        return event;
    }
}
