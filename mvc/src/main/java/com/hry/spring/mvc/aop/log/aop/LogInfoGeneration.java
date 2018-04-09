package com.hry.spring.mvc.aop.log.aop;

import com.hry.spring.mvc.aop.log.annotation.EventType;
import com.hry.spring.mvc.aop.log.annotation.LogKey;
import com.hry.spring.mvc.aop.log.manager.model.LogAdmModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by huangrongyou@yixin.im on 2018/4/9.
 */
@Component
public class LogInfoGeneration {

    public boolean processingManagerLogMessage(ProceedingJoinPoint jp, LogAdmModel logBean, String optEvent) {
        boolean isSuccess = true;
        try {
            if (optEvent.equals(EventType.LOGIN)) {
                // 进行登陆判断
                Integer result = (Integer) jp.proceed();
                if(result != null && result.intValue() == 200){
                    isSuccess = true;
                }else {
                    isSuccess = false;
                }
            } else {
                // 管理员操作信息
                processingAdmOpt(jp, logBean, optEvent);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

    public void processingAdmOpt(ProceedingJoinPoint jp, LogAdmModel logBean, String optEvent) {
        String message = "";
        // 获取第一个参数的值
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
                    sb.append(fieldName).append(";").append(f.get(object));
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

}
