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
			String s = session.getId();
			//List<Broadcast> broadcasts = (List<Broadcast>) application.getAttribute("broadcasts");
			ArrayList<Movie> movies = (ArrayList<Movie>) application.getAttribute("movies");
			ArrayList<Cinema> cinemas = (ArrayList<Cinema>) application.getAttribute("cinemas");
			ArrayList<Hall> halls = (ArrayList<Hall>) application.getAttribute("halls");
		%>
	</head>
	<body>
		<select name="movieSelect" form="addBroadcastForm">
			<% for( Movie m : movies){ %>
		 		 <option value="<%= m.getTitle()  %>"><%= m.getTitle()  %></option>
			<% } %>
		</select>
		
		<select name="cinemaSelect" form="addBroadcastForm">
			<% for( Cinema c : cinemas){ %>
		 		 <option value="<%= c.getId() %>"> <%= c.getId()  %></option>
			<% } %>
		</select>
		
		<select name="hallSelect" form="addBroadcastForm">
			<% for( Hall h : halls){ %>
		 		 <option value="<%= h.getId()  %>"><%= h.getId()  %></option>
			<% } %>
		</select>
		<form action="addBroadcast" method="post" id="addBroadcastForm" name="addBroadcastForm">
			<input type="submit" value="addBroadcast">
		</form> 	
		
	</body>
</html>