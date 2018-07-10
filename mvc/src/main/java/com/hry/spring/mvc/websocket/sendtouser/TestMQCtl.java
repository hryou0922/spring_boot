package com.hry.spring.mvc.websocket.sendtouser;

import com.alibaba.fastjson.JSON;
import com.hry.spring.mvc.websocket.model.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "login")
    public String login(HttpServletRequest request, @RequestParam(required=true) String name, String pwd){
        HttpSession httpSession = request.getSession();
        // 如果登录成功，则保存到会话中
        httpSession.setAttribute("loginName", name);
        return "websocket/sendtouser/ws-sendtouser-rabbitmq";
    }


    @RequestMapping(value="/broadcast-rabbitmq/index")
    public String broadcastIndex(){
        return "websocket/sendtouser/ws-sendtouser-rabbitmq";
    }

    /**
     * 向执行用户发送请求
     * @param msg
     * @param userName
     * @return
     */
    @RequestMapping(value = "send2user")
    @ResponseBody
    public int sendMq2User(String msg, String userName){
        System.out.println("===========" + msg + "=======" + userName);
        RequestMessage demoMQ = new RequestMessage();
        demoMQ.setName(msg);

        template.convertAndSendToUser(userName, "/topic/demo", JSON.toJSONString(demoMQ));
       // template.convertAndSend("/topic/demo", CommonJsonUtils.toJsonString(demoMQ));
        return 0;
    }
}
