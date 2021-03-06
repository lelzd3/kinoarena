<%@page import="java.util.ArrayList"%>
<%@page import="pojos.Broadcast"%>
<%@page import="java.util.List"%>
<%@page import="pojos.User"%>
<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Admin Page</title>
		<%
		//can also be User user = (User) session.getAttribute("user")
			User admin = (User) request.getSession().getAttribute("admin"); 
			String s = session.getId();
		//	ArrayList<Broadcast> broadcasts = (ArrayList<Broadcast>) application.getAttribute("broadcasts");
		%>
	</head>
	
	<body>
	
		<h1 align="center">Hello admin, <%= admin.getUsername()  %></h1>
		<h2 align="center">Session id is <%= s %></h2>
		<br>

		<form action="addMovie.jsp" method="get">
			<button type="submit">Add Movie</button>
		</form>
		<br>
		
		<form action="addHall.jsp" method="get">
			<button type="submit">Add Hall</button>
		</form>
		<br>
		
		<form action="addCinema.jsp" method="get">
			<button type="submit">Add Cinema</button>
		</form>
		<br>
		
		<form action="addBroadcast.jsp" method="get">
			<button type="submit">Add Broadcast</button>
		</form>
		<br>
		
		<form action="removeBroadcast.jsp" method="get">
			<button type="submit">Remove Broadcast</button>
		</form>
		<br>
		
		<form action="removeMovie.jsp" method="get">
			<button type="submit">Remove Movie</button>
		</form>
		<br>
		
		<form action="removeCinema.jsp" method="get">
			<button type="submit">Remove Cinema</button>
		</form>
		<br>
		
		<form action="removeHall.jsp" method="get">
			<button type="submit">Remove Hall</button>
		</form>
		<br>
		
		<form action="makeAdmin.jsp" method="get">
			<button type="submit">Make someone an admin</button>
		</form>
		<br>
		
		<form action="setDiscount.jsp" method="get">
			<button type="submit">Make discount on a broadcast </button>
		</form>
		<br>
		
		
		<form action="logout" method="get">
			<input type="submit" value="Logout"><br>
		</form>
	
	</body>
</html>