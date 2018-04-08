package com.hry.spring.mvc.aop.log.aop;

import com.hry.spring.mvc.aop.log.annotation.LogModule;
import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
public class CommonMethod {
//    /**
//     *  * 
//     *  * 获取接口名称
//     *  * 
//     *  * @param jp
//     *  * @return
//     *  
//     */
//    @Deprecated
//    public static String getInterfaceClassName(JoinPoint jp) {
//        Class aClass = jp.getSignature().getDeclaringType();
//        return aClass.getSimpleName();
//    }

    /**
     *  * 获取连接点的类的被LogModule修饰的LogModule
     *      jp.getSignature(): 只获取拦截类对应的接口对象
     *  * @param jp
     *  * @return
     *  
     */
    public static LogModule getLogModule(JoinPoint jp) {
        LogModule module = null;
        Class target = jp.getTarget().getClass();
        Class[] intf = target.getInterfaces();
        for (Class item : intf) {
            if (item.getAnnotation(LogModule.class) != null) {
                module = (LogModule) item.getAnnotation(LogModule.class);
                break;
            }
        }
        return module;
    }

    /**
     *  * 从LogModule上获取PojoClass
     *  * @param jp
     *  * @return
     *  
     */
    public static Class convertPojoClass(JoinPoint jp) {
        LogModule entityAn = getLogModule(jp);
        if (entityAn == null) {
            return null;
        }
        Class pojoClass = entityAn.pojoClass();
        return pojoClass;
    }

    public static Class convertBeanClass(JoinPoint jp) {
        LogModule entityAn = getLogModule(jp);
        if (entityAn == null) {
            return null;
        }
        Class beanClass = entityAn.beanClass();
        return beanClass;
    }

    /**
     *  * 从LogModule上获取LogModule的名称
     *  * @param jp
     *  * @return
     *  
     */
    public static String convertModule(JoinPoint jp) {
        String module = "-1";
        LogModule entityAn = getLogModule(jp);
        if (entityAn == null) {
            return "-1";
        }
        module = entityAn.module().getModule();
        return module;
    }


    /**
     *  * 获取连接点的参数列表
     *  * @param jp
     *  * @return
     *  
     */
    public static Class[] toClassArray(JoinPoint jp) {
        Object[] objs = jp.getArgs();
        List classList = new ArrayList();
        for (Object obj : objs) {
            if (obj instanceof ArrayList)
                classList.add(List.class);
            else if (obj instanceof LinkedList)
                classList.add(List.class);
            else if (obj instanceof HashMap)
                classList.add(Map.class);
            else if (obj instanceof HashSet)
                classList.add(Set.class);
            else if (obj == null)
                classList.add(null);
            else {
                classList.add(obj.getClass());
            }
        }
        Class[] clazzs = (Class[]) classList.toArray(new Class[0]);
        for (Method method : jp.getTarget().getClass().getMethods()) {
            System.out.println("sss" + method);
        }
        System.out.println(Arrays.toString(clazzs));
//        String methodName = jp.getSignature().getName();
////        if ((getInterfaceClassName(jp).equals("BaseManager"))
////                && (!(isDeleteEvent(methodName)))) {
////            // 除了delete外，其他方法，第一个通常为搁置Bean类，需要将其替换为Object
////            clazzs[0] = Object.class;
////        }
        return clazzs;
    }

    private static boolean isDeleteEvent(String methodName) {
        return (methodName.startsWith("delete"));
    }

}
