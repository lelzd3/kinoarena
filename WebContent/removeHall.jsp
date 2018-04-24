<%@page import="pojos.Hall"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Remove Hall page</title>

		<%
			ArrayList<Hall> halls = (ArrayList<Hall>) application.getAttribute("halls");
		%>
	</head>
	
	<body>
	
		<form action="removeHall" method="post" >
			<br>
			<select name=hallSelect>
				<% for (Hall hall : halls) { %>
				<option value="<%= hall.getId() %>"><%=hall.getId() +" "+ hall.getSeats() + " "%></option>
				<% } %>
			</select>
			<br>
			<input type="submit" value="removeHall">
		</form> 
	
	</body>
</html>