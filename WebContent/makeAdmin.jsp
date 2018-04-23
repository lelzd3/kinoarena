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
			//TODO make it so it gets all USERS- not admin
			ArrayList<User> users = (ArrayList<User>) application.getAttribute("users");	
		%>
	</head>
	<body>
		
		<form action="makeAdmin" method="post" id="makeAdminForm" name="makeAdminForm">
			<br>
			<select name="usersSelect">
				<% for( User user : users){ %>
			 		 <option value="<%= user.getEmail() %>"><%= user.getUsername()  %></option>
				<% } %>
			</select>
			<br>
			<input type="submit" value="makeAdmin">
		</form>
	</body>
</html>