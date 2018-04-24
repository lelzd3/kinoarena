<%@page import="dao.MovieDao"%>
<%@page import="pojos.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pojos.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>All Movies page</title>
		
		<style>
		.one{
			border: 1px solid darkslategray;
			font-size: 150%;
		}
		</style>
		
		
		<%
		//can also be User user = (User) session.getAttribute("user")
			User user = (User) request.getSession().getAttribute("user"); 
		//
		//	ArrayList<Movie> movies = (ArrayList<Movie>) application.getAttribute("movies");
			ArrayList<Movie> movies = (ArrayList<Movie>) MovieDao.getInstance().getAllMovies();
		%>
	</head>
	
	<body>
		
		<% for (Movie movie : movies) { %>
		<br><br>
			<div class="one">
				<strong><%= movie.getTitle() %></strong>
				<br><br>
				<img src="getPic?title=<%=movie.getTitle()%>" height="250" width="250">
				<br><br>
				<p><%= movie.getDescription() %></p>
				<br>
				<p>Duration: <%=movie.getDuration() %></p>
				Rating: <%=movie.getRating() %>
				<br>
				<form action="rateMovie" method="post">
					<input type="hidden" name="movieIdToBeRated" value="<%= movie.getId() %>">
					<select name="ratingSelect">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
					<input type="submit" value="rateMovie">
				</form>
			</div>
		<br><br>
		<% } %>
		
		
	</body>
	
</html>