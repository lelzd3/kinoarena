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
import pojos.Broadcast;
import pojos.Reservation;
import pojos.User;


@WebServlet("/reserve")
public class ReservationHallServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			int broadcastId = 9;
			Broadcast broadcast = BroadcastDao.getInstance().getBroadcastById(broadcastId);
//			System.out.println("Stignah tyk");
			//append them to request
			ArrayList<String> allSeatsForBroadcast = ReservationDao.getInstance().getAllOccupiedSeatsForABroadcast(broadcast);
			response.getWriter().write(allSeatsForBroadcast.toString().substring(1, allSeatsForBroadcast.toString().length()-1));
			//request.setAttribute("reserved_seats", allSeatsForBroadcast.toString());
			//forward to jsp 
			//request.getRequestDispatcher("reservationHall.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			request.setAttribute("exception", e);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String s = (String) request.getParameter("hiddenSeats");
		String[] allSeats = s.split(",");
		for(int i = 0 ; i < allSeats.length ; i++) {
			String[] rowAndCow = allSeats[i].split(" ");
			int row = Integer.parseInt(rowAndCow[0].replaceAll("\\D+",""));
			int col = Integer.parseInt(rowAndCow[1].replaceAll("\\D+",""));
			try {
				ReservationDao.getInstance().bookSelectedSeats(row,col,1);
				System.out.println("Succesfull booking");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("reservationHall.jsp").forward(request, response);
	}
}
