package com.hry.spring.mvc.websocket.model;

/**
 * 服务端返回给浏览器的消息
 */
public class ResponseMessage {
    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
