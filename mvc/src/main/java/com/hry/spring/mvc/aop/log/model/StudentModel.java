package com.hry.spring.mvc.aop.log.model;

import com.hry.spring.mvc.aop.log.annotation.LogKey;

/**
 * Created by huangrongyou@yixin.im on 2018/4/8.
 */
public class StudentModel {
    @LogKey(isUserId = true)
    private String id; // 编号
    private String name;  // 名称
    private Integer age; // 年龄
    private String grade;  // 年级

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
