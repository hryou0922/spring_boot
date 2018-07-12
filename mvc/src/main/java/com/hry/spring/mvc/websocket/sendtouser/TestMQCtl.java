package com.hry.spring.mvc.websocket.sendtouser;

import com.alibaba.fastjson.JSON;
import com.hry.spring.mvc.websocket.model.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by huangrongyou@yixin.im on 2018/7/9.
 */
@Controller
@RequestMapping(value = "/ws")
public class TestMQCtl {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 模拟登录
     * @param request
     * @param name
     * @param pwd
     * @return
     */
    @RequestMapping(value = "loginIn", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam(required=true) String name, String pwd){
        HttpSession httpSession = request.getSession();
        // 如果登录成功，则保存到会话中
        httpSession.setAttribute("loginName", name);
        return "websocket/sendtouser/ws-sendtouser-rabbitmq";
    }

    /**
     * 转到登录页面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(){
        // 转到登录页面
        return "websocket/sendtouser/login";
    }

    /**
     * websocket页面
     * @return
     */
    @RequestMapping(value="/broadcast-rabbitmq/index")
    public String broadcastIndex(){
        return "websocket/sendtouser/ws-sendtouser-rabbitmq";
    }

    /**
     * 发送页面
     * @param msg
     * @param userName
     * @return
     */
    @RequestMapping(value = "send")
    public String sendMq2UserPage(String msg, String userName){
        return "websocket/sendtouser/send";
    }
    /**
     * 向执行用户发送请求
     * @param msg
     * @param name
     * @return
     */
    @RequestMapping(value = "send2user")
    @ResponseBody
    public int sendMq2User(String msg, String name){
        System.out.println("===========" + msg + "=======" + name);
        RequestMessage demoMQ = new RequestMessage();
        demoMQ.setName(msg);

        template.convertAndSendToUser(name, "/topic/demo", JSON.toJSONString(demoMQ));
       // template.convertAndSend("/topic/demo", CommonJsonUtils.toJsonString(demoMQ));
        return 0;
    }

}
