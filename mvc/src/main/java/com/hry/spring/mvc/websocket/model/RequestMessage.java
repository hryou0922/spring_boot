package com.hry.spring.mvc.websocket.model;

/**
 *  浏览器向服务端请求的消息
 */
public class RequestMessage {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
