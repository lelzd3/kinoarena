<%@page import="pojos.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Remove Movie page</title>
		
		<%
			ArrayList<Movie> movies = (ArrayList<Movie>) application.getAttribute("movies");
		%>
	</head>
	<body>
	
		<form action="removeMovie" method="post" >
			<br>
			<select name=movieSelect>
				<% for (Movie movie : movies) { %>
				<option value="<%= movie.getId() %>"><%=movie.getId() +" "+ movie.getTitle() + " " + movie.getDuration()+" "%></option>
				<% } %>
			</select>
			<br>
			<input type="submit" value="removeMovie">
		</form> 
	
	</body>
</html>