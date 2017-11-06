<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form  name="myform" method="post"  action="httpEntity">
		<table>
			<tr>
				<td>First Name:</td>
				<td><input type="text" value="name" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" value="lastName" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Save Changes" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>