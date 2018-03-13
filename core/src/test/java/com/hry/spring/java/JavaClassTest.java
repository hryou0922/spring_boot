package com.hry.spring.java;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by huangrongyou@yixin.im on 2018/3/13.
 */
public class JavaClassTest {


    @Test
    public void getPrimitiveClass() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(Integer.TYPE);
        System.out.println(Integer.class);
        System.out.println(String[].class);
        System.out.println(String[][].class);

        System.out.println("=====================");
        for(Method methods : Class.class.getDeclaredMethods()){
            System.out.println(methods.toGenericString());

        }

        Method getPrimitiveClassMethod = Class.class.getDeclaredMethod("getPrimitiveClass", String.class);
        getPrimitiveClassMethod.setAccessible(true);
        getPrimitiveClassMethod.getAnnotations();
        System.out.println(getPrimitiveClassMethod.invoke(null, "int"));

    }

}
