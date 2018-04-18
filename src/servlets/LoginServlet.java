package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.InvalidDataException;
import exceptions.WrongCredentialsException;
import pojos.User;
import servlets.sessionutil.Sessions;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Sessions.validateSession(request, response);
		
		
		try {
			
			String username = request.getParameter("username");
			String pass = request.getParameter("password");
			//TODO the rest as well
			User u = dao.UserDao.getInstance().getUser(username, pass);
			if(u != null) {
				request.getSession().setAttribute("user", u);
				request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
			}
			else {
				throw new WrongCredentialsException("invalid username or password");
			}
			
		}
		catch (WrongCredentialsException e) {
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
		}
		
	}
	

}
