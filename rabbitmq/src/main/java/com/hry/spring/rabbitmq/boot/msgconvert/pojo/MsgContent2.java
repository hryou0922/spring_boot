package com.hry.spring.rabbitmq.boot.msgconvert.pojo;

/**
 * 测试发送对象
 * Created by huangrongyou@yixin.im on 2018/3/1.
 */
public class MsgContent2 {
    private String id;
    private String content;

    @Override
    public String toString(){
        return "[ id = " + id + "; " + " content = " + content + " ]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
