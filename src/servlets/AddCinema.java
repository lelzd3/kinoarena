package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminManager;
import exceptions.InvalidDataException;
import exceptions.NotAnAdminException;
import pojos.Cinema;
import pojos.Hall;
import pojos.User;


@WebServlet("/addCinema")
public class AddCinema extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User admin = (User) (User) request.getSession().getAttribute("admin");
			String address = request.getParameter("address");
			String name = request.getParameter("name");
			
			Cinema newCinema = new Cinema(name, address);
			AdminManager.getInstance().addNewCinema(newCinema, admin);
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
