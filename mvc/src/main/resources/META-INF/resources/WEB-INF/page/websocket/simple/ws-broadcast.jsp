<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Boot+WebSocket+广播式</title>
</head>
<body>

<body onload="disconnect()">
<div>
    <div>
        <button id="connect" onclick="connect();">连接</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
    </div>
    <div id="conversationDiv">
        <label>输入你的名字</label><input type="text" id="name" />
        <button id="sendName" onclick="sendName();">发送</button>
        <p id="response"></p>
    </div>
</div>

<!-- jquery  -->
<script src="/websocket/jquery.js"></script>
<!-- stomp协议的客户端脚本 -->
<script src="/websocket/stomp.js"></script>
<!-- SockJS的客户端脚本 -->
<script src="/websocket/sockjs.js"></script>

<script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
        $('#response').html();
    }

    function connect() {
        // websocket的连接地址，此值等于WebSocketMessageBrokerConfigurer中registry.addEndpoint("/websocket-simple").withSockJS()配置的地址
        var socket = new SockJS('/websocket-simple'); //1
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            console.log('Connected: ' + frame);
            // 客户端订阅消息的目的地址：此值BroadcastCtl中被@SendTo("/topic/getResponse")注解的里配置的值
            stompClient.subscribe('/topic/getResponse', function(respnose){ //2
                showResponse(JSON.parse(respnose.body).responseMessage);
            });
        });
    }


    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function sendName() {
        var name = $('#name').val();
        // 客户端消息发送的目的：服务端使用BroadcastCtl中@MessageMapping("/receive")注解的方法来处理发送过来的消息
        stompClient.send("/receive", {}, JSON.stringify({ 'name': name }));
    }

    function showResponse(message) {
        var response = $("#response");
        response.html(message + "<br\>" + response.html());
    }
</script>
</body>

</body>
</html>