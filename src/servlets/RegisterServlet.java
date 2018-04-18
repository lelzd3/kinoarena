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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			String pass1 = request.getParameter("password1");
			String pass2 = request.getParameter("password2");
			String email = request.getParameter("email");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			if (username.isEmpty() || username.length() < 5) {
				throw new WrongCredentialsException("username must be at least 5 chars long");
			}
			if (!pass1.equals(pass2)) {
				throw new WrongCredentialsException("passwords missmatch");
			}
			if (!email.contains("@") || email.isEmpty()) {
				throw new WrongCredentialsException("Invalid entered email");
			}
			if (firstName.isEmpty()) {
				throw new WrongCredentialsException("Invalid entered first name");
			}
			if (lastName.isEmpty()) {
				throw new WrongCredentialsException("Invalid entered last name");
			}
			// creating new user with these details
			User u = new User(username, pass1, firstName, lastName, email);
			// adding to db
			UserDao.getInstance().addUser(u);
			request.getRequestDispatcher("WEB-INF/main.jsp").forward(request, response);

		}
		catch (WrongCredentialsException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
