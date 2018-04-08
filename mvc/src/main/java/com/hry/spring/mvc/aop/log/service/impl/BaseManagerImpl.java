package com.hry.spring.mvc.aop.log.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hry.spring.mvc.aop.log.manager.model.LogAdmModel;
import com.hry.spring.mvc.aop.log.service.BaseManager;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
public class BaseManagerImpl<Pojo, Bean> implements BaseManager<Pojo, Bean> {

    @Override
    public void insertLog(LogAdmModel paramLogAdmBean) {
        System.out.println("===" + this.getClass() + ": dealLog" + JSONObject.toJSONString(paramLogAdmBean));
    }

    @Override
    public void deleteById(Long paramLong) {
        System.out.println("===" + this.getClass() + ": deleteById" );
    }

    @Override
    public Long save(Bean paramBean, Class<Pojo> paramClass) {
        System.out.println("===" + this.getClass() + ": save" );
        return 0l;
    }

    @Override
    public void update(Bean paramBean, Class<Pojo> paramClass) {
        System.out.println("===" + this.getClass() + ": update" );
    }
}
