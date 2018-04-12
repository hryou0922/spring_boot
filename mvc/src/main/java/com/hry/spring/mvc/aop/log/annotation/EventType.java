package com.hry.spring.mvc.aop.log.annotation;

/**
 * 事情类型
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
public enum EventType {
    DEFAULT("1", "default"), ADD("2", "add"), UPDATE("3", "update"), DELETE_SINGLE("4", "delete-single"),
    LOGIN("10","login"),LOGIN_OUT("11","login_out");

    private EventType(String index, String name){
        this.name = name;
        this.event = index;
    }
    private String event;
    private String name;
    public String getEvent(){
        return this.event;
    }

    public String getName() {
        return name;
    }
}
