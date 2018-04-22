package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDao;
import dao.BroadcastDao;
import pojos.Broadcast;
import pojos.User;

@WebServlet("/removeBroadcastServlet")
public class RemoveBroadcast extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		try {
//			//get genders from db
//			List<Broadcast> broadcasts = (List<Broadcast>) BroadcastDao.getInstance().getAllBroadcasts();
//			//add them to request
//			request.setAttribute("broadcasts", broadcasts);
//			//forward this request to removeBroadcast.jsp
//			request.getRequestDispatcher("removeBroadcast.jsp").forward(request, response);
//		} catch (Exception e) {
//			request.setAttribute("exception", e);
//			request.getRequestDispatcher("error.jsp").forward(request, response);
//		}
//	}
//	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User admin = (User) request.getSession().getAttribute("admin");
			// if not work , should try with long because of types in DB
			
			int broadcastId = Integer.parseInt(request.getParameter("broadcastSelect"));
			
			Broadcast broadcastToDelete = BroadcastDao.getInstance().getBroadcastById(broadcastId);
			//TODO use ADMIN MANAGER -> IF ADMINMANAGER doesnt work -> fix manager
			AdminDao.getInstance().removeBroadcast(broadcastToDelete, admin);
			request.getRequestDispatcher("adminMain.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}


}
