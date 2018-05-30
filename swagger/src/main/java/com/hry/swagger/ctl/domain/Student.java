package com.hry.swagger.ctl.domain;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by huangrongyou@yixin.im on 2018/5/29.
 */
@ApiModel( description = "学生")
public class Student {
    @ApiModelProperty(value = "主键id")
    private String id;
    @ApiModelProperty(value = "名称", required = true)
    private String name;
    @ApiModelProperty(value = "年龄", required = true)
    private int age;

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

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
