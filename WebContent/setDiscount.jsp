<%@page import="pojos.Broadcast"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pojos.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Set discount on a broadcast </title>
<%
	User admin = (User) request.getSession().getAttribute("admin");	
	ArrayList<Broadcast> broadcasts = (ArrayList<Broadcast>) application.getAttribute("broadcasts");

%>
</head>
<body>

		<br>
		<select name="broadcastSelect" form="addBroadcastForm">
			<% for( Broadcast b : broadcasts){ %>
		 		 <option value="<%= b.getId() %>"><%= b.getId() +" "+ b.getMovieId() + " " + b.getProjectionTime()+" " %></option>
			<% } %>
		</select>
		
		<form action="setDiscount" method="post" id="setDiscountForm" name="setDiscountForm">
			<input type="number" name="percent">
			<input type="submit" value="setDiscount">
		
</body>
</html>