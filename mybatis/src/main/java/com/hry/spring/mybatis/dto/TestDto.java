package com.hry.spring.mybatis.dto;

import java.util.Date;

/**
 * 定义dto层数据
 */
public class TestDto {

    private Integer id;

    private Integer age;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
