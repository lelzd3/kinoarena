package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminManager;
import dao.BroadcastDao;
import dao.MovieDao;
import pojos.Broadcast;
import pojos.Movie;
import pojos.User;

@WebServlet("/removeMovie")
public class RemoveMovie extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User admin = (User) request.getSession().getAttribute("admin");
			
			int movieId = Integer.parseInt(request.getParameter("movieSelect"));
			Movie movieToDelete=MovieDao.getInstance().getMovieById(movieId);

			AdminManager.getInstance().removeMovie(movieToDelete, admin);
			request.getRequestDispatcher("adminMain.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
