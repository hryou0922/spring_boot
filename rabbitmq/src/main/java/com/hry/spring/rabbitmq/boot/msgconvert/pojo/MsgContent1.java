package com.hry.spring.rabbitmq.boot.msgconvert.pojo;

/**
 * 测试发送对象
 * Created by huangrongyou@yixin.im on 2018/3/1.
 */
public class MsgContent1 {
    private String name;
    private String age;

    @Override
    public String toString(){
        return "[ name = " + name + "; " + " age = " + age + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
