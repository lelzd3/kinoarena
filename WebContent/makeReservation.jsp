<%@page import="pojos.Broadcast"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BroadcastDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Reservation</title>
		<%
			ArrayList<Broadcast> broadcasts = (ArrayList<Broadcast>) BroadcastDao.getInstance().getAllBroadcasts();
		%>
	</head>
	
	<body>
	
		<form action="reserve" method="post">
			<br>
			Select Broadcast
			<select name="broadcastSelect">
				<% for( Broadcast broadcast : broadcasts){ %>
			 		 <option value="<%= broadcast.getId() %>"><%= broadcast.getMovieId() +" "+ broadcast.getProjectionTime()  %></option>
				<% } %>
			</select>
			<br>
			<input type="submit">
		</form>
	
	</body>
</html>