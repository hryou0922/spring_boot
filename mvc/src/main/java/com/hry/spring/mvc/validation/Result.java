package com.hry.spring.mvc.validation;

/**
 * Created by huangrongyou@yixin.im on 2018/3/14.
 */
public class Result {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
