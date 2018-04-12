package com.hry.spring.mvc.aop.log.service;

import com.hry.spring.mvc.aop.log.model.StudentModel;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
public interface IStudentService {

    void deleteById(String id, String a);

    int save(StudentModel studentModel);

    void update(StudentModel studentModel);

    void queryById(String id);
}
