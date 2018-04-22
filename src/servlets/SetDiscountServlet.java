package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminManager;
import dao.BroadcastDao;
import exceptions.InvalidDataException;
import exceptions.NotAnAdminException;
import pojos.Broadcast;
import pojos.User;

@WebServlet("/setDiscount")
public class SetDiscountServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			int broadcastId = Integer.parseInt(request.getParameter("broadcastSelect")); 
			int percentForDiscount = Integer.parseInt(request.getParameter("percent"));
			
			Broadcast broadcastForDiscount = BroadcastDao.getInstance().getBroadcastById(broadcastId);
			AdminManager.getInstance().setPromoPercent((User) request.getSession().getAttribute("admin") , broadcastForDiscount, percentForDiscount);
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
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}
