<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Register</title>
	</head>
	<body>
			<h1>Register</h1>
			<form action="register" method="post">
			<table align="center">
		      	<tr>
	              	<td>Username:</td>
	              	<td><input type="text" class="w3-border w3-padding" name="username" required value="Stanislav1"></td>
	            </tr>
	            <tr>
	              	<td>Password:</td>
	              	<td><input type="password" class="w3-border w3-padding" name="password1" required value="#12345678sS"></td>
	            </tr>
	            <tr>
	              	<td>Confirm Password:</td>
	              	<td><input type="password" class="w3-border w3-padding" name="password2" required value="#12345678sS"></td>
	            </tr>
	            <tr>
	              	<td>Email:</td>
	              	<td><input type="email" class="w3-border w3-padding" name="email" required value="stan123@abv.bg"></td>
	            </tr>
	            <tr>
	              	<td>First Name:</td>
	              	<td><input type="text" class="w3-border w3-padding" name="firstName" required value="Stanchy"></td>
	            </tr>
	            <tr>
	              	<td>Last Name:</td>
	              	<td><input type="text" class="w3-border w3-padding" name="lastName" required value="Kaliparski"></td>
	            </tr>
	           </table>
	           <button style="margin:auto;display:block" type="submit">Register</button><br>
	           <a href="login.jsp">Already have an account? Click here to login.</a>
			</form>
	</body>
</html>