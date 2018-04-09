package com.hry.spring.mvc.aop.log.service.impl;

import com.alibaba.fastjson.JSON;
import com.hry.spring.mvc.aop.log.annotation.EventType;
import com.hry.spring.mvc.aop.log.annotation.LogEnable;
import com.hry.spring.mvc.aop.log.annotation.LogEvent;
import com.hry.spring.mvc.aop.log.annotation.ModuleType;
import com.hry.spring.mvc.aop.log.model.StudentModel;
import com.hry.spring.mvc.aop.log.service.IStudentService;
import org.springframework.stereotype.Service;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
@Service
@LogEnable // 启动日志拦截
@LogEvent(module = ModuleType.STUDENT)
public class StudentServiceImpl implements IStudentService {
    @Override
    public void deleteById(String id) {
        System.out.printf(this.getClass() +  "deleteById  id = " + id);
    }

    @Override
    @LogEvent(event = EventType.ADD) // 添加日志标识
    public int save(StudentModel studentModel) {
        System.out.printf(this.getClass() +  "save  save = " + JSON.toJSONString(studentModel));
        return 1;
    }

    @Override
    @LogEvent(event = EventType.UPDATE) // 添加日志标识
    public void update(StudentModel studentModel) {
        System.out.printf(this.getClass() +  "save  update = " + JSON.toJSONString(studentModel));
    }

    // 没有日志标识
    @Override
    public void queryById(String id) {
        System.out.printf(this.getClass() +  "queryById  id = " + id);
    }
}
