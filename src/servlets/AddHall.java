package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminManager;
import exceptions.InvalidDataException;
import exceptions.NotAnAdminException;
import pojos.Broadcast;
import pojos.Hall;
import pojos.User;


@WebServlet("/addHall")
public class AddHall extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User admin = (User) (User) request.getSession().getAttribute("admin");
			int cinema_id = Integer.parseInt(request.getParameter("cinemaSelect"));
			//TODO extract in some constant maybe or put it in constructor
			// cuz in addBroadcast in BrDao puts 100 in the insert query
			// but here in addHall in HallDao we in the insert query we put h.getSeats()
			int seats = Integer.parseInt(request.getParameter("seats"));
			
			Hall newHall = new Hall(seats, cinema_id);
			AdminManager.getInstance().addNewHall(newHall, admin);
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
