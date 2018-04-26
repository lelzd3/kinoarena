package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BroadcastDao;
import dao.ReservationDao;
import pojos.Broadcast;
import pojos.Reservation;
import pojos.User;


@WebServlet("/reserveInterim")
public class ReserveInterimServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			int broadcast_id = Integer.parseInt(request.getParameter("broadcastSelect"));
			
			request.getSession().setAttribute("session_broadcast_id", broadcast_id);
			request.getRequestDispatcher("reservationHall.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}
