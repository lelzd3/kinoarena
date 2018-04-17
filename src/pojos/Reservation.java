package pojos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.sun.media.sound.InvalidDataException;

public class Reservation {
	
	private static final int MAX_SEATS_FOR_A_RESERVATIONS = 8;
	private int id;
	private int user_id;
	private int broadcast_id;
	private int seats_number;
	private ArrayList<Seat> allSeatsReserved;
	private LocalDateTime timeReservationIsMade;
	
	
	public Reservation(int user_id, int broadcast_id) {
		setUser_id(user_id);
		setBroadcast_id(broadcast_id);
		this.allSeatsReserved = new ArrayList<Seat>();
		this.timeReservationIsMade = LocalDateTime.now();
	}
	
	public Reservation(int user_id, int broadcast_id, ArrayList<Seat> allSeatsReserved) throws InvalidDataException {
		setUser_id(user_id);
		setBroadcast_id(broadcast_id);
		setAllSeatsReserved(allSeatsReserved);
		setSeats_number(allSeatsReserved.size());
		this.timeReservationIsMade = LocalDateTime.now();
	}
	
	public Reservation(int id,int user_id, int broadcast_id, ArrayList<Seat> allSeatsReserved) throws InvalidDataException {
		this(user_id, broadcast_id, allSeatsReserved);
		setId(id);
	}

	public Reservation(int id, int user_id, int broadcast_id, ArrayList<Seat> allSeatsReserved,
			LocalDateTime timeReservationIsMade) throws InvalidDataException {
		setId(id);
		setUser_id(user_id);
		setBroadcast_id(broadcast_id);
		setAllSeatsReserved(allSeatsReserved);
		setSeats_number(allSeatsReserved.size());
		setTimeReservationIsMade(timeReservationIsMade);
	}
	
	public Reservation(int id, int user_id, int broadcast_id,int seats_number,
			LocalDateTime timeReservationIsMade) throws InvalidDataException {
		setId(id);
		setUser_id(user_id);
		setBroadcast_id(broadcast_id);
		setSeats_number(seats_number);
		setTimeReservationIsMade(timeReservationIsMade);
	}

	//getters:
	
	public int getId() {
		return id;
	}
	
	
	public int getUser_id() {
		return user_id;
	}
	
	
	public int getBroadcast_id() {
		return broadcast_id;
	}
	
	
	public LocalDateTime getTimeReservationIsMade() {
		return timeReservationIsMade;
	}

	public ArrayList<Seat> getAllSeatsReserved() {
		return allSeatsReserved;
	}
	
	public int getSeats_number() {
		return seats_number;
	}
	//setters:
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public void setBroadcast_id(int broadcast_id) {
		this.broadcast_id = broadcast_id;
	}
	
	public void setTimeReservationIsMade(LocalDateTime timeReservationIsMade) throws InvalidDataException {
		if(timeReservationIsMade == null) {
			throw new InvalidDataException("Invalid data entered in reservation pojo");
		}
		this.timeReservationIsMade = timeReservationIsMade;
	}


	public void setAllSeatsReserved(ArrayList<Seat> allSeatsReserved) throws InvalidDataException {
		if(allSeatsReserved == null || allSeatsReserved.isEmpty()) {
			throw new InvalidDataException("Oops, error in setting reserved seats in reservation ");
		}
		this.allSeatsReserved = allSeatsReserved;
	}
	
	public void setSeats_number(int seats_number) throws InvalidDataException {
		if(seats_number < 0 || seats_number > MAX_SEATS_FOR_A_RESERVATIONS) {
			throw new InvalidDataException("Invalid number of seats");
		}
		else {
			this.seats_number = seats_number;
		}
	}


	


	


	

	


	

}
