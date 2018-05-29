package com.hry.swagger.ctl.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by huangrongyou@yixin.im on 2018/5/29.
 */
@ApiModel( description = "登录参数")
public class Student {
    @ApiModelProperty(value = "标题", notes="note")
    private String id;
    @ApiModelProperty(value = "用户编号", required = true)
    private String name;
    private int age;
    private String a;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
