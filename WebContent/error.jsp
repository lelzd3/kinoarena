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
		<title>error</title>
	</head>
	<body>
		<h1>Error...</h1>
		<% Exception e = (Exception) request.getAttribute("exception"); %>
		<h2>Reason: <%= e.getMessage() %></h2>
		<h3>Exception <%= e.getStackTrace() %></h3>
		<h4>Excp <%= e.getLocalizedMessage() %></h4>
		<h5>E <%= e.getCause() %></h5>
		<h6>E <%= e.getClass().getName() %></h6>
		<a href="login.jsp">Back to login, sorry about that</a>
		
	</body>
</html>