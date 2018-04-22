<%@page import="java.util.ArrayList"%>
<%@page import="pojos.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make someone an admin</title>
<%
		User admin = (User) request.getSession().getAttribute("admin");
		ArrayList<User> users = (ArrayList<User>) application.getAttribute("users");
		
%>
</head>
<body>

		<br>
		<select name="usersSelect" form="makeAdminFrom">
			<% for( User u : users){ %>
		 		 <option value="<%= u.getEmail() %>"><%= u.getEmail()  %></option>
			<% } %>
		</select>
		
		<br>
		<form action="makeAdmin" method="post" id="makeAdminForm" name="makeAdminForm">
			<input type="email" name="email">
			<input type="submit" value="makeAdmin">

</body>
</html>