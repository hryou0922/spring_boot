package com.hry.spring.mvc.websocket.cluster;


import java.security.Principal;

/**
 * Created by huangrongyou@yixin.im on 2018/7/10.
 */
public class MyPrincipal implements Principal {
    private String loginName;

    public MyPrincipal(String loginName){
        this.loginName = loginName;
    }
    @Override
    public String getName() {
        return loginName;
    }
}
