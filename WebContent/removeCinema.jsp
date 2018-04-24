<%@page import="pojos.Cinema"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Remove Cinema page</title>

		<%
			ArrayList<Cinema> cinemas = (ArrayList<Cinema>) application.getAttribute("cinemas");
		%>
	</head>
	
	<body>
	
		<form action="removeCinema" method="post" >
			<br>
			<select name=cinemaSelect>
				<% for (Cinema cinema : cinemas) { %>
				<option value="<%= cinema.getId() %>"><%=cinema.getId() +" "+ cinema.getName() + " " + cinema.getAddress()+" "%></option>
				<% } %>
			</select>
			<br>
			<input type="submit" value="removeCinema">
		</form> 
	
	</body>
</html>