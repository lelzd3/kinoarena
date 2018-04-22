package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminManager;
import dao.UserDao;
import exceptions.InvalidDataException;
import exceptions.NotAnAdminException;
import pojos.User;


@WebServlet("/makeAdmin")
public class MakeAdmin extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		//get the username of this user who we want to be an admin
		String usernameToBeAdmin = request.getParameter("email");
		User userToBeAdmin = UserDao.getInstance().getUserByEmail(usernameToBeAdmin);
		AdminManager.getInstance().changeUserIsAdminStatus((User) request.getSession().getAttribute("admin"), usernameToBeAdmin);
		request.getRequestDispatcher("adminMain.jsp").forward(request, response);
		
		
		}
		catch (InvalidDataException e) {
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
