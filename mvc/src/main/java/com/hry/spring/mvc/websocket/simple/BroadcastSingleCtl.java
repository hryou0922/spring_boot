package com.hry.spring.mvc.websocket.simple;

import com.alibaba.fastjson.JSONObject;
import com.hry.spring.mvc.websocket.model.RequestMessage;
import com.hry.spring.mvc.websocket.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class BroadcastSingleCtl {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastSingleCtl.class);

    // 收到消息记数
    private AtomicInteger count = new AtomicInteger(0);

    // @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
    @MessageMapping("/receive-single")
    /**
     * 基于WebSocket的STOMP有个属性@SendTo。
     * @SendTo默认 消息将被发送到与传入消息相同的目的地，但是目的地前面附加前缀（默认情况下为“/topic”}）。
     * 也可以使用endToUser}批注，可以将将消息定向到特定用户
     * 消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换。
     *
     * 这里使用 @SendToUser，而不是使用 @SendTo
     */
    @SendToUser("/topic/getResponse")
    public ResponseMessage broadcast(RequestMessage requestMessage){
        logger.info("receive message = {}" , JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("BroadcastCtlSingle receive [" + count.incrementAndGet() + "] records");
        return responseMessage;
    }


    @RequestMapping(value="/broadcast-single/index")
    public String broadcastIndex(){
        return "websocket/simple/ws-broadcast-single";
    }

}
