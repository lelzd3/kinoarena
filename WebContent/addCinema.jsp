<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add Cinema</title>
		
	</head>
	
	<body>
		<form action="addCinema" method="post">
			
			Name <input type="text" name="name" required>
			<br><br>
			Address <input type="text" name="address" required>
			<br><br>
			<input type="submit" value="addCinema">
		</form> 	
		<br>
	</body>
</html>