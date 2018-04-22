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

@WebServlet("/RemoveBroadcast")
public class RemoveBroadcast extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			//get genders from db
			List<Broadcast> broadcasts = (List<Broadcast>) BroadcastDao.getInstance().getAllBroadcasts();
			//add them to request
			req.setAttribute("broadcasts", broadcasts);
			//forward this request to removeBroadcast.jsp
			req.getRequestDispatcher("removeBroadcast.jsp").forward(req, resp);
		} catch (Exception e) {
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}
	

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession s = req.getSession();
			User admin = (User) s.getAttribute("user");
			// if not work , should try with long because of types in DB
			int broadcastId = Integer.parseInt(req.getParameter("broadcastId"));
			Broadcast broadcastToDelete = BroadcastDao.getInstance().getBroadcastById(broadcastId);
			AdminDao.getInstance().removeBroadcast(broadcastToDelete, admin);
		} catch (Exception e) {
			System.out.println("Sql : " + e.getMessage());
		}

	}


}
