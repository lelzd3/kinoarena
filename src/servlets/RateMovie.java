package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminManager;
import controller.UserManager;
import dao.MovieDao;
import dao.UserDao;
import pojos.Movie;
import pojos.User;

@WebServlet("/rateMovie")
public class RateMovie extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			int movieIdToBeRated = Integer.parseInt(request.getParameter("movieIdToBeRated"));
			int newRating = Integer.parseInt(request.getParameter("ratingSelect"));
			
			Movie movie = MovieDao.getInstance().getMovieById(movieIdToBeRated);
			UserDao.getInstance().rateMovie(user, movie, newRating);
			request.getRequestDispatcher("viewAllMovies.jsp").forward(request, response);
		}catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
