<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>模拟发送消息</title>
</head>
<body>

<form action="login" method="post">
        接收者用户:<input type="text" id="name" name="name" value="<%=session.getAttribute("loginName") %>" />
    <p>
        消息内容:<input type="text" id="msg" name="msg" />
    <p>
        <input type="button" id="send" value="发送" />
</form>


<script src="/websocket/jquery.js"></script>
<script type=text/javascript>

    $("#send").click(function(){
        $.post("send2user",
            {
                name: $('#name').val(),
                msg: $('#msg').val()
            },
            function(data, status){
                alert("Data: " + data + "\nStatus: " + status);
            });
    });
</script>

</body>
</html>