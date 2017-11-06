<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- enctype=”multipart/form-data”是必须设置的属性 --> 
	<form enctype="multipart/form-data" method="post" action="upload">
		<input type="text" name="name"/> <br />
		<input type="file" name="file" />  <br />
		<input type="submit" value="submit" /> <br />
	</form>
</body>
</html>