package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminManager;
import dao.CinemaDao;
import dao.MovieDao;
import pojos.Cinema;
import pojos.Movie;
import pojos.User;


@WebServlet("/removeCinema")
public class RemoveCinema extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User admin = (User) request.getSession().getAttribute("admin");
			
			int cinemaId = Integer.parseInt(request.getParameter("cinemaSelect"));
			Cinema cinemaToDelete = CinemaDao.getInstance().getCinemaById(cinemaId);

			AdminManager.getInstance().removeCinema(cinemaToDelete,admin);
			request.getRequestDispatcher("adminMain.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
