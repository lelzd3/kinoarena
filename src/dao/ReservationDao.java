package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	public void addReservation(User u, Reservation r, ArrayList<Seat> seats) throws SQLException {
		// TODO Auto-generated method stub	
	}

	@SuppressWarnings("resource")
	@Override
	public void deleteReservation(Reservation r) throws SQLException {
		//Transaction remove from reservations table
		// then remove all from reservations_seats where id = reservation id
		
		PreparedStatement s = null;
		try {
			connection.setAutoCommit(false);
			s = connection.prepareStatement("DELETE FROM reservations WHERE id = ?");
			s.setInt(1, r.getId());
			s.executeUpdate();
			
			s =  connection.prepareStatement("DELETE FROM reservation_seats WHERE ticket_reservations_id = ?");
			s.setInt(1, r.getId());
			s.executeUpdate();
			connection.commit();
		}
		catch (SQLException e){
			connection.rollback();
			throw e;
		}
		finally {
			s.close();
			connection.setAutoCommit(true);
		}

	}

	@Override
	public Collection<Reservation> getAllReservations() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Reservation> getAllReservationsForABroadcast(Broadcast b) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
