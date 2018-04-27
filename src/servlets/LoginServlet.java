package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BroadcastDao;
import dao.CinemaDao;
import dao.HallDao;
import dao.MovieDao;
import dao.UserDao;
import exceptions.InvalidDataException;
import exceptions.WrongCredentialsException;
import pojos.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Sessions.validateSession(request, response);
		
		try {
			getServletConfig().getServletContext().setAttribute("broadcasts", BroadcastDao.getInstance().getAllBroadcasts());
			getServletConfig().getServletContext().setAttribute("movies", MovieDao.getInstance().getAllMovies());
			getServletConfig().getServletContext().setAttribute("halls", HallDao.getInstance().getAllHalls());
			getServletConfig().getServletContext().setAttribute("cinemas", CinemaDao.getInstance().getAllCinemas());
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			UserDao.getInstance().loginCheck(username, password);
			User user = UserDao.getInstance().getUser(username);
			if(user.getIsAdmin()) {

				request.getSession().setAttribute("admin", user);
			
				//TODO USE USER MANAGER
				
				getServletConfig().getServletContext().setAttribute("users", UserDao.getInstance().getAllUsers());
				request.getRequestDispatcher("adminMain.jsp").forward(request, response);
				
			}
			else {
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("WEB-INF/main.jsp").forward(request, response);
			}
			
		}
		catch (WrongCredentialsException e) {
			//TODO maybe forward to login with failed login message
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		catch (InvalidDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}/* catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}*/
		
	}
	

}
