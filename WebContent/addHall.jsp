<%@page import="pojos.Cinema"%>
<%@page import="pojos.Hall"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pojos.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add hall page</title>
		<%
			ArrayList<Cinema> cinemas = (ArrayList<Cinema>) application.getAttribute("cinemas");
		%>
	</head>
	
	<body>
	
		<form action="addHall" method="post">
			<br>
			Select Cinema
			<select name="cinemaSelect">
				<% for( Cinema cinema : cinemas){ %>
			 		 <option value="<%= cinema.getId() %>"> <%= cinema.getName()  %></option>
				<% } %>
			</select>
			<br>
			Seats <input type="number" required name="seats">
			<input type="submit" value="addHall">
		</form> 	
		<br>
	
	</body>
</html>