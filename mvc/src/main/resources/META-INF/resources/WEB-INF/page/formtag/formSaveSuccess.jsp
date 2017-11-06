<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page session="false" %>
<html>
<head>
	<title>user Saved Successfully</title>
</head>
<body>
<h3>
	user Saved Successfully.
</h3>

<strong>user firstName:${user.firstName}</strong><br>
<strong>user lastName:${user.lastName}</strong><br>
<strong>user preferences:${user.preferences}</strong><br>
<strong>user sex:${user.sex}</strong><br>
<strong>user password:${user.password}</strong><br>
<strong>user skills:${user.skills}</strong><br>
<strong>user house:${user.house}</strong><br>
<strong>user country:${user.country}</strong><br>
<strong>user notes:${user.notes}</strong><br>
 

</body>
</html>