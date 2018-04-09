package com.hry.spring.mvc.aop.log.annotation;

/**
 * 模块类型
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
public enum ModuleType {
    DEFAULT("1"), // 默认值
    STUDENT("2"),// 学生模块
    TEACHER("3"); // 用户模块

    private ModuleType(String index){
        this.module = index;
    }
    private String module;
    public String getModule(){
        return this.module;
    }
}
