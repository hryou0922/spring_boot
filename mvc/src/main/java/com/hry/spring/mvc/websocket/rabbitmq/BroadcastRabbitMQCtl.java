package com.hry.spring.mvc.websocket.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.hry.spring.mvc.websocket.model.RequestMessage;
import com.hry.spring.mvc.websocket.model.ResponseMessage;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class BroadcastRabbitMQCtl {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastRabbitMQCtl.class);

    // 收到消息记数
    private AtomicInteger count = new AtomicInteger(0);

    // @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
    @MessageMapping("/receive-rabbitmq")
    /**
     * 基于WebSocket的STOMP有个属性@SendTo。
     * @SendTo默认 消息将被发送到与传入消息相同的目的地，但是目的地前面附加前缀（默认情况下为“/topic”}）
     */
   // @SendTo("/exchange/rabbitmq/get-response")
  //  @SendTo("/queue/rabbitmq")
  //  @SendTo("/amq/queue/rabbitmq2")
    @SendTo("/topic/get-response")
    public ResponseMessage broadcast(RequestMessage requestMessage){
        logger.info("receive message = {}" , JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage("BroadcastRabbitMQCtl receive [" + count.incrementAndGet() + "] records");
        return responseMessage;
    }


    @RequestMapping(value="/broadcast-rabbitmq/index")
    public String broadcastIndex(){
        return "websocket/rabbitmq/ws-broadcast-rabbitmq";
    }

    @RequestMapping(value="/ipInfo")
    @ResponseBody
    public String ipInfo(HttpServletRequest request){
        String ip = request.getRemoteAddr();
        return ip;
    }

}
