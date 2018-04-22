package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import exceptions.WrongCredentialsException;
import pojos.User;

@WebServlet("/addBroadcast")
public class AddBroadcast extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		try {
			String movieName = request.getParameter("movieSelect");
			int cinema_id = Integer.parseInt(request.getParameter("cinemaSelect"));
			int hall_id = Integer.parseInt(request.getParameter("hallSelect"));
			

		}

}
