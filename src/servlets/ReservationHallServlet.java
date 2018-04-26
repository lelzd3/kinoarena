package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminManager;
import dao.BroadcastDao;
import dao.ReservationDao;
import pojos.Broadcast;
import pojos.Reservation;
import pojos.User;


@WebServlet("/reserve")
public class ReservationHallServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//get all reserved seats
//			int broadcastId = Integer.parseInt(request.getParameter("broadcastSelect"));
			int broadcastId = 8;
			Broadcast broadcast = BroadcastDao.getInstance().getBroadcastById(broadcastId);
			//append them to request
			ArrayList<String> allSeatsForBroadcast = ReservationDao.getInstance().getAllOccupiedSeatsForABroadcast(broadcast);
			System.out.println(1);
			System.out.println(allSeatsForBroadcast.toString());
			response.getWriter().write(allSeatsForBroadcast.toString().substring(1, allSeatsForBroadcast.toString().length()-1));
			//request.setAttribute("reserved_seats", allSeatsForBroadcast.toString());
			//forward to jsp 
			//request.getRequestDispatcher("reservationHall.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

}
