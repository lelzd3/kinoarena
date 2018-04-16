package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

import database.DBManager;
import pojos.Broadcast;
import pojos.Reservation;
import pojos.Seat;
import pojos.User;

public class ReservationDao implements IReservationDao {

	private static ReservationDao instance;
	private Connection connection;
	
	public synchronized static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private ReservationDao() {
		connection = DBManager.getInstance().getConnection();
	}

	@Override
	public void addReservation(User u, Reservation r, ArrayList<Seat> seats) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReservation(Reservation r) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Reservation> getAllReservations() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Reservation> getAllReservationsForABroadcast(Broadcast b) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
