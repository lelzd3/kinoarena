<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>login page</title>
	</head>
	<body>
		<h1>Login</h1>
		
		<form action="login" method="post">
			Username<input type="text" name="username" value="Stanislav1"><br>
			Password<input type="password" name="password" value="#12345678sS"><br>
			<input type="submit" value="Login"><br>
			Don`t have an account? <a href="register.jsp">Register here</a>
		</form>
		
	</body>
</html>