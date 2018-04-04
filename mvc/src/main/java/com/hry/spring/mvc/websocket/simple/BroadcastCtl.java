package com.hry.spring.mvc.websocket.simple;

import com.alibaba.fastjson.JSONObject;
import com.hry.spring.mvc.websocket.model.RequestMessage;
import com.hry.spring.mvc.websocket.model.ResponseMessage;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class BroadcastCtl {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastCtl.class);

    // 收到消息记数
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo默认 消息将被发送到与传入消息相同的目的地
     * 消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换
     * @param requestMessage
     * @return
     */
    @MessageMapping("/receive")
    @SendTo("/topic/getResponse")
    public ResponseMessage broadcast(RequestMessage requestMessage){
        logger.info("receive message = {}" , JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("BroadcastCtl receive [" + count.incrementAndGet() + "] records");
        return responseMessage;
    }


    @RequestMapping(value="/broadcast/index")
    public String broadcastIndex(HttpServletRequest req){
        System.out.println(req.getRemoteHost());
        return "websocket/simple/ws-broadcast";
    }

}
