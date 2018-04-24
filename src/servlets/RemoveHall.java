package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminManager;
import dao.HallDao;
import dao.MovieDao;
import pojos.Hall;
import pojos.Movie;
import pojos.User;

@WebServlet("/removeHall")
public class RemoveHall extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User admin = (User) request.getSession().getAttribute("admin");
			
			int hallId = Integer.parseInt(request.getParameter("hallSelect"));
			Hall hallToDelete = HallDao.getInstance().getHallById(hallId);

			AdminManager.getInstance().removeHall(hallToDelete, admin);
			request.getRequestDispatcher("adminMain.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
