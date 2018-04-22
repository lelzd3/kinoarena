package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.AdminManager;
import dao.AdminDao;
import dao.BroadcastDao;
import dao.UserDao;
import exceptions.InvalidDataException;
import exceptions.NotAnAdminException;
import exceptions.WrongCredentialsException;
import pojos.Broadcast;
import pojos.User;

@WebServlet("/addBroadcast")
public class AddBroadcast extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			int movie_id = Integer.parseInt(request.getParameter("movieSelect"));
			int cinema_id = Integer.parseInt(request.getParameter("cinemaSelect"));
			int hall_id = Integer.parseInt(request.getParameter("hallSelect"));
			
			String date = request.getParameter("projection_time").concat(":00");
//			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
			LocalDateTime projection_time =LocalDateTime.parse(date);
		
			int free_sits = Integer.parseInt(request.getParameter("free_sits"));
			double price = Double.parseDouble(request.getParameter("price"));
		
			
			Broadcast newBroadcast = new Broadcast(cinema_id, movie_id, hall_id, projection_time, price);
			AdminManager.getInstance().addNewBroadcast(newBroadcast,(User) request.getSession().getAttribute("admin"));
			request.getRequestDispatcher("adminMain.jsp").forward(request, response);
			
		} catch (InvalidDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (NotAnAdminException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
			
	}
	
	

}
