<%@page import="pojos.Hall"%>
<%@page import="pojos.Cinema"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pojos.Movie"%>
<%@page import="pojos.Broadcast"%>
<%@page import="java.util.List"%>
<%@page import="pojos.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add Broadcast page</title>
		<%
		//can also be User user = (User) session.getAttribute("user")
			User admin = (User) request.getSession().getAttribute("admin");
			//List<Broadcast> broadcasts = (List<Broadcast>) application.getAttribute("broadcasts");
			ArrayList<Movie> movies = (ArrayList<Movie>) application.getAttribute("movies");
			ArrayList<Cinema> cinemas = (ArrayList<Cinema>) application.getAttribute("cinemas");
			ArrayList<Hall> halls = (ArrayList<Hall>) application.getAttribute("halls");
			//TODO make getAllHallsForCinema() so we can show all hows for the specific ciname chosen in the select
		%>
	</head>
	<body>
	
		<br>
		<select name="movieSelect" form="addBroadcastForm">
			<% for( Movie m : movies){ %>
		 		 <option value="<%= m.getId() %>"><%= m.getTitle()  %></option>
			<% } %>
		</select>
		<br>
		
		<br>
		<select name="cinemaSelect" form="addBroadcastForm">
			<% for( Cinema c : cinemas){ %>
		 		 <option value="<%= c.getId() %>"> <%= c.getName()  %></option>
			<% } %>
		</select>
		<br>
		
		<br>
		<select name="hallSelect" form="addBroadcastForm">
			<% for( Hall h : halls){ %>
		 		 <option value="<%= h.getId()  %>"><%= h.getId()  %></option>
			<% } %>
		</select>
		<br>
		
		<br>
		<form action="addBroadcast" method="post" id="addBroadcastForm" name="addBroadcastForm">
			<input type="datetime-local" name="projection_time">
			<br>
			<input type="number" name= "free_sits">
			<br>
			<input type="number" name= "price">
			<br>
			<input type="submit" value="addBroadcast">
		</form> 	
		<br>
		
	</body>
</html>