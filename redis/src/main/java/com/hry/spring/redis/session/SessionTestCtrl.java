package com.hry.spring.redis.session;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangrongyou@yixin.im on 2018/1/25.
 */
@RestController
public class SessionTestCtrl {

    /**
     * 模拟登陆，在session中存储一个值
     * http://127.0.0.1:8080/login
     * @param request
     * @return
     */
    @RequestMapping("login")
    public Map<String,Object> login(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        // 设置session中的值
        httpSession.setAttribute("username", "hry" + System.currentTimeMillis());

        Map<String,Object> rtnMap = new HashMap<>();
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        while(attributeNames.hasMoreElements()){
            String name = attributeNames.nextElement();
            rtnMap.put(name, httpSession.getAttribute(name));
        }
        rtnMap.put("sessionId", httpSession.getId());
        return rtnMap;
    }

    /**
     * 从session中获取值
     * http://127.0.0.1:8080/get-session
     * @param request
     * @return
     */
    @RequestMapping("get-session")
    public Object getSession(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Map<String,Object> rtnMap = new HashMap<>();
        Enumeration<String> attributeNames = request.getSession().getAttributeNames();
        while(attributeNames.hasMoreElements()){
            String name = attributeNames.nextElement();
            rtnMap.put(name, httpSession.getAttribute(name));
        }
        int count;
        try {
            count = Integer.parseInt(String.valueOf(httpSession.getAttribute("count")));
            count++;
        }catch (NumberFormatException e){
            count = 1;
        }
        httpSession.setAttribute("count",count+"");

        rtnMap.put("sessionId", httpSession.getId());
        return rtnMap;
    }

    @RequestMapping("invalidate")
    public int invalidate(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return 1;
    }

}
