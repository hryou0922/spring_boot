package com.hry.spring.mvc.aop.log.service.impl;

import com.hry.spring.mvc.aop.log.service.AlarmTypeManager;
import com.hry.spring.mvc.aop.log.model.AlarmType;
import com.hry.spring.mvc.aop.log.model.AlarmTypeBean;
import org.springframework.stereotype.Service;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Service
public class AlarmTypeManagerImpl extends BaseManagerImpl<AlarmType, AlarmTypeBean> implements AlarmTypeManager{

    @Override
    public void updateAll(String paramString1, String paramString2) {
        System.out.println("===" + this.getClass() + ": updateAll" );
    }

    @Override
    public void saveAlarmType(AlarmTypeBean paramAlarmTypeBean) {
        System.out.println("===" + this.getClass() + ": saveAlarmType" );
    }

    @Override
    public void updateAlarmType(AlarmTypeBean paramAlarmTypeBean) {
        System.out.println("===" + this.getClass() + ": updateAlarmType" );
    }
}
