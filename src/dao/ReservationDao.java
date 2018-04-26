package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import database.DBManager;
import exceptions.InvalidDataException;
import pojos.Broadcast;
import pojos.Reservation;
import pojos.Seat;

public class ReservationDao implements IReservationDao {

	private static ReservationDao instance;
	private Connection connection;

	public synchronized static ReservationDao getInstance() {
		if (instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}

	private ReservationDao() {
		connection = DBManager.getInstance().getConnection();
	}

	@Override
	public void addReservation(Reservation r, ArrayList<Seat> seats) throws SQLException {

		PreparedStatement s = null;
		try {
			connection.setAutoCommit(false);
			s = connection.prepareStatement(
					"INSERT INTO reservations (users_id,broadcast_id,seats_number,time) VALUES (?,?,?,?) ");
			s.setInt(1, r.getUser_id());
			s.setInt(2, r.getBroadcast_id());
			s.setInt(3, r.getAllSeatsReserved().size());
			Date date = Date.valueOf(r.getTimeReservationIsMade().toLocalDate());
			s.setDate(4, date);
			s.executeUpdate();

			for (Seat seat : seats) {
				s = connection.prepareStatement(
						"INSERT INTO reservations_seats (ticket_reservations,row_number,column_number) VALUES (?,?,?)");
				s.setInt(1, r.getId());
				s.setInt(2, seat.getRow());
				s.setInt(3, seat.getColumn());
				s.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} finally {
			s.close();
			connection.setAutoCommit(true);
		}
	}

	@SuppressWarnings("resource")
	@Override
	public void deleteReservation(Reservation r) throws SQLException {
		// Transaction remove from reservations table
		// then remove all from reservations_seats where id = reservation id

		PreparedStatement s = null;
		try {
			connection.setAutoCommit(false);
			s = connection.prepareStatement("DELETE FROM reservations WHERE id = ?");
			s.setInt(1, r.getId());
			s.executeUpdate();

			s = connection.prepareStatement("DELETE FROM reservation_seats WHERE ticket_reservations_id = ?");
			s.setInt(1, r.getId());
			s.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} finally {
			s.close();
			connection.setAutoCommit(true);
		}

	}

	@Override
	public Collection<Reservation> getAllReservationsForABroadcast(Broadcast b)
			throws SQLException, InvalidDataException {
		PreparedStatement s = connection.prepareStatement(
				"SELECT id,users_id,broadcasts_id,seats_number,time FROM reservations WHERE broadcasts_id = ?");
		s.setInt(1, b.getId());
		ArrayList<Reservation> reservations = new ArrayList<>();
		ResultSet result = s.executeQuery();
		while (result.next()) {
			LocalDateTime time = result.getTimestamp("time").toLocalDateTime();
			Reservation r = new Reservation(result.getInt("id"), result.getInt("users_id"),
					result.getInt("broadcasts_id"), result.getInt("seats_number"), time);
			reservations.add(r);
		}

		return reservations;
	}

	// TODO put in interface
	public ArrayList<String> getAllOccupiedSeatsForABroadcast(Broadcast broadcast)
			throws SQLException, InvalidDataException {

		ArrayList<String> allSeats = new ArrayList<String>();
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);

			ArrayList<Reservation> reservations = (ArrayList<Reservation>) ReservationDao.getInstance()
					.getAllReservationsForABroadcast(broadcast);

			// TODO optimise with 1 query , dynamically apend ? to string
			for (Reservation reservation : reservations) {
				// do a select query WHERE reservation_id = reservation.getId
				ps = connection.prepareStatement(
						"SELECT row_number,column_number FROM reservations_seats WHERE ticket_reservations_id = ?");
				ps.setInt(1, reservation.getId());
				ResultSet result = ps.executeQuery();
				// make a resultset and while through him and append to allSeats
				while (result.next()) {

					allSeats.add(result.getInt("row_number") + "_" + result.getInt("column_number"));

				}

			}

			connection.commit();

		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} finally {
			ps.close();
			connection.setAutoCommit(true);

		}
		return allSeats;

	}

	public void bookSelectedSeats(int row, int col, int ticketReservId) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = connection
					.prepareStatement("INSERT INTO reservations_seats (row_number,column_number,ticket_reservations_id) VALUES (?,?,?) ");
			ps.setInt(1, row);
			ps.setInt(2, col);
			ps.setInt(3, ticketReservId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			ps.close();
		}
	}
}
