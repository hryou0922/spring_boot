package com.hry.spring.mvc.aop.log.model;

import com.hry.spring.mvc.aop.log.annotation.LogKey;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
public class AlarmTypeBean {
    @LogKey(isLog=true)
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
