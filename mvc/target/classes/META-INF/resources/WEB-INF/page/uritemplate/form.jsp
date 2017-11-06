<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

===================@requestParam===================
<form action="/springmvc/mvc/owners/requestParam" method="post">    
     	参数inputStr:<input type="text" name="inputStr">    
     	参数intputInt:<input type="text" name="inputInt">    
     <input type="submit" value="submit" />
</form>
==================================================<br/>
<br/>
===================@requestBody===================
<form action="/springmvc/mvc/owners/requestBody" method="post">    
     	参数inputStr:<input type="text" name="inputStr">    
     	参数intputInt:<input type="text" name="inputInt">    
     <input type="submit" value="submit" />
</form>
==================================================<br/>
<br/>
===================@responseBody===================
<form action="/springmvc/mvc/owners/responseBody" method="post">    
     	参数inputStr:<input type="text" name="inputStr">    
     	参数intputInt:<input type="text" name="inputInt">    
     <input type="submit" value="submit" />
</form>
==================================================<br/>

</body>
</html>