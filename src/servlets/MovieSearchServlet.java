package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDao;
import exceptions.InvalidDataException;
import pojos.Movie;


@WebServlet("/search")
public class MovieSearchServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("movie");
		
		try {
			Movie selectedMovie = MovieDao.getInstance().getMovieByName(name);
			
			req.getSession().setAttribute("selectedMovie", selectedMovie);
			req.getRequestDispatcher("viewAMovie.jsp").forward(req, resp);
		} catch (SQLException e) {
			System.out.println("SQL Bug: " + e.getMessage());
		} catch (InvalidDataException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			
		}
		
		
	}
}
