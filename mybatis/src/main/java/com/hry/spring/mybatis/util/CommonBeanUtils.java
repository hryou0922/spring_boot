package com.hry.spring.mybatis.util;


import org.springframework.beans.BeanUtils;

/**
 * Created by huangrongyou@yixin.im on 2017/9/6.
 */
public class CommonBeanUtils {
    public static void copyProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target);
    }
}
