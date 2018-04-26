package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.AdminManager;
import dao.BroadcastDao;
import dao.ReservationDao;
import exceptions.InvalidDataException;
import pojos.Broadcast;
import pojos.Reservation;
import pojos.Seat;
import pojos.User;


@WebServlet("/reserve")
public class ReservationHallServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//int broadcastId = Integer.parseInt(request.getParameter("broadcastSelect"));
			//int broadcastId = 8;
			int broadcastId = (Integer)request.getSession().getAttribute("session_broadcast_id");
			Broadcast broadcast = BroadcastDao.getInstance().getBroadcastById(broadcastId);
			//append them to request
			ArrayList<String> allSeatsForBroadcast = ReservationDao.getInstance().getAllOccupiedSeatsForABroadcast(broadcast);
			response.getWriter().write(allSeatsForBroadcast.toString().substring(1, allSeatsForBroadcast.toString().length()-1));
			//request.setAttribute("reserved_seats", allSeatsForBroadcast.toString());
			//forward to jsp 
			//request.getRequestDispatcher("reservationHall.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			int broadcast_id = (Integer)request.getSession().getAttribute("session_broadcast_id");
			request.getSession().setAttribute("session_broadcast_id", broadcast_id);
			
			String s = (String) request.getParameter("hiddenSeats");
			if(s == "") {
				//try with isEmpty()

				request.getRequestDispatcher("viewAllMovies.jsp").forward(request, response);
				return;
			}
			String[] allSeats = s.split(",");
			ArrayList<Seat> selectedSeats = new ArrayList<Seat>();
			
			for(int i = 0 ; i < allSeats.length ; i++) {
				String[] rowAndCow = allSeats[i].split(" ");
				int row = Integer.parseInt(rowAndCow[0].replaceAll("\\D+",""));
				int col = Integer.parseInt(rowAndCow[1].replaceAll("\\D+",""));
				
				Seat newSeat = new Seat(row, col);
				selectedSeats.add(newSeat);
			}
			
			Reservation reservation = new Reservation(user.getId(), broadcast_id, selectedSeats);
			ReservationDao.getInstance().addReservation(reservation, selectedSeats);
			request.getRequestDispatcher("viewAllMovies.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} catch (InvalidDataException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
}
