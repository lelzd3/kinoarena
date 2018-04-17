package dao;

import java.util.ArrayList;
import java.util.Collection;

import pojos.Broadcast;
import pojos.Reservation;
import pojos.Seat;
import pojos.User;

public interface IReservationDao {

	public void addReservation(Reservation r,ArrayList<Seat> seats) throws Exception;
	
	public void deleteReservation(Reservation r) throws Exception;
	
	public Collection<Reservation> getAllReservationsForABroadcast(Broadcast b) throws Exception;

	
}
